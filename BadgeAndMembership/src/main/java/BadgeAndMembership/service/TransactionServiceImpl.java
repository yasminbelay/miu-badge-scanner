package BadgeAndMembership.service;

import BadgeAndMembership.model.*;
import BadgeAndMembership.repository.*;
import contracts.dto.TransactionDto;
import contracts.dto.TransactionRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl extends BaseReadServiceImpl<TransactionDto, Transaction, Integer> implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TransactionDto add(TransactionRequestDto requestDto) throws ResourceNotFoundException {
        Transaction transaction = getValidatedTransaction(requestDto);
        return modelMapper.map(transactionRepository.save(transaction), TransactionDto.class);
    }

    @Override
    public void update(Integer id, TransactionDto transactionDto) throws ResourceNotFoundException {
        Transaction transaction = baseRepository.findById(id).orElse(null);
        if (transaction == null) {
            throw new ResourceNotFoundException();
        }
        transaction = modelMapper.map(transaction, Transaction.class);
        transaction.setId(id);
        baseRepository.save(transaction);
    }

    @Override
    public void deleteById(Integer id) {
        baseRepository.deleteById(id);
    }

    // Transaction creation and validation logics
    private Transaction getValidatedTransaction(TransactionRequestDto requestDto) throws ResourceNotFoundException {
        Plan plan = planRepository.findById(requestDto.getPlanId()).orElseThrow(() -> new ResourceNotFoundException());
        Location location = locationRepository.findById(requestDto.getLocationId()).orElseThrow(() -> new ResourceNotFoundException());
        Badge badge = badgeRepository.findByBadgeUid(requestDto.getBadgeUid()).orElseThrow(() -> new ResourceNotFoundException());

        //creating transaction
        Transaction transaction = new Transaction();
        transaction.setDatetime(LocalDateTime.now());
        transaction.setBadge(badge);
        transaction.setLocation(location);

        //check if badge is active
        if (!badge.getIsActive()) {
            transaction.setStatus(TransactionStatus.DECLINED);
            transaction.setRemark("Badge is inactive");
            return transaction;
        }

        //validate the transaction and set status
        Membership validMembership = getValidMembershipForPlan(plan, badge);
        if (validMembership == null) {
            transaction.setStatus(TransactionStatus.DECLINED);
            transaction.setRemark("No membership for plan");
            return transaction;
        }

        //set membership as valid membership
        transaction.setMembership(validMembership);

//        //check is there membership is valid for timeslot and check for single entry
//        if (!isValidTimeSlot(location, validMembership)) {
//            transaction.setStatus(TransactionStatus.DECLINED);
//            return transaction;
//        }

        //check if timeslot is open
        Timeslot currentOpenTimeslot = getCurrentOpenTimeSlot(location);
        if (currentOpenTimeslot == null) {
            transaction.setStatus(TransactionStatus.DECLINED);
            transaction.setRemark("No timeslot open at current time");
            return transaction;
        }

        //check if user has allowance remaining or not
        if (!hasRemainingAllowance(validMembership)) {
            transaction.setStatus(TransactionStatus.DECLINED);
            transaction.setRemark("No allowance remaining for this cycle");
            return transaction;
        }

        //check if single entry per timeslot has been used
        if (validMembership.getIsSingleEntryPerTimeSlot() && isSingleEntryPerTimeSlotUsed(currentOpenTimeslot, validMembership)) {
            transaction.setStatus(TransactionStatus.DECLINED);
            transaction.setRemark("Single timeslot entry has been used already");
            return transaction;
        }

        //check if capacity has reached for the timeslot
        if (isCapacityFullForTimeslot(currentOpenTimeslot, location.getCapacity())) {
            transaction.setStatus(TransactionStatus.DECLINED);
            transaction.setRemark("Capacity for current timeslot is full");
            return transaction;
        }

        //allow transaction if all checks passes
        transaction.setStatus(TransactionStatus.ALLOWED);
        transaction.setRemark("");

        //decrease number of allowance for allowed transaction
        if (validMembership.getMembershipType() == MembershipType.LIMITED)
            validMembership.setNumberOfAllowanceLimit(validMembership.getNumberOfAllowanceLimit() - 1);

        return transaction;
    }

    private Membership getValidMembershipForPlan(Plan plan, Badge badge) {
        //check if member has allowed role for the plan
        if (!isMemberRoleAllowedInPlan(plan, badge.getMember())) {
            return null;
        }
        //get valid membership for plan
        Collection<Membership> memberships = membershipRepository.findAllByMember_Id(badge.getMember().getId());
        Membership validMembershipForPlan = memberships
                .stream()
                .filter(membership -> membership.isMembershipActive()
                        && membership.getPlan().getId() == plan.getId())
                .findFirst().orElse(null);

        return validMembershipForPlan;
    }

    //private methods to validate transaction
    private boolean isMemberRoleAllowedInPlan(Plan plan, Member member) {
        List<String> planAllowedRoles = plan.getAllowedRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
        List<String> memberRoles = member.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
        for (String role : planAllowedRoles) {
            if (memberRoles.contains(role)) return true;
        }
        return false;
    }

    private boolean hasRemainingAllowance(Membership membership) {
        //check if not limited
        if (membership.getMembershipType() != MembershipType.LIMITED) {
            return true;
        }
        return membership.getNumberOfAllowanceLimit() > 0;
    }

    private Timeslot getCurrentOpenTimeSlot(Location location) {
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
        Timeslot openTimeSlot = location.getTimeslots().stream()
                .filter(timeslot -> timeslot.getDayOfWeek() == dayOfWeek)
                .filter(timeslot -> !(LocalTime.now().isBefore(timeslot.getStartTime()))
                        && !(LocalTime.now().isAfter(timeslot.getEndTime())))
                .findFirst().orElse(null);
        return openTimeSlot;
    }

    private boolean isSingleEntryPerTimeSlotUsed(Timeslot timeslot, Membership membership) {
        LocalDateTime timeslotStartDateTime = LocalDateTime.of(LocalDate.now(), timeslot.getStartTime());
        LocalDateTime timeslotEndDateTime = LocalDateTime.of(LocalDate.now(), timeslot.getEndTime());
        //get allowed entry count for the member in current timeslot
        int usedTransactionInTimeslot = transactionRepository.countAllowedByMembership_IdAndDatetimeBetween(membership.getId(), timeslotStartDateTime, timeslotEndDateTime);
        return usedTransactionInTimeslot > 0;
    }

    private boolean isCapacityFullForTimeslot(Timeslot timeslot, int capacity) {
        LocalDateTime timeslotStartDateTime = LocalDateTime.of(LocalDate.now(), timeslot.getStartTime());
        LocalDateTime timeslotEndDateTime = LocalDateTime.of(LocalDate.now(), timeslot.getEndTime());
        //get allowed entry count in current timeslot
        int entryCount = transactionRepository.countAllowedByDatetimeBetween(timeslotStartDateTime, timeslotEndDateTime);
        return entryCount >= capacity;
    }
}
