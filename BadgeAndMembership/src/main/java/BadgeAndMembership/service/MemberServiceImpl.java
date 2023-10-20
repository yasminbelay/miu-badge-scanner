package BadgeAndMembership.service;

import BadgeAndMembership.model.*;
import BadgeAndMembership.model.Role;
import BadgeAndMembership.repository.MemberRepository;
import BadgeAndMembership.repository.MembershipRepository;
import BadgeAndMembership.repository.RoleRepository;
import contracts.dto.*;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberServiceImpl extends BaseReadServiceImpl<MemberDto, Member, Integer> implements MemberService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Logger logger;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void add(MemberRequestDto memberDto) {
        logger.info("MemberService: Adding member :{}" + memberDto);
        Member member = mapper.map(memberDto, Member.class);
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Collection<Role> roles = member.getRoles().stream().map(role -> roleRepository.findByName(role.getName())).collect(Collectors.toList());
        member.setRoles(roles);
        baseRepository.save(member);
    }

    @Override
    public void update(int id, MemberRequestDto memberDto) throws ResourceNotFoundException {
        logger.info("MemberService: Updating member with id: " + id + " to: {}" + memberDto);
        Member member = baseRepository.findById(id).orElse(null);
        if (member == null) {
            throw new ResourceNotFoundException();
        }
        Member newMember = mapper.map(memberDto, Member.class);
        newMember.setId(id);
        Collection<Role> roles = newMember.getRoles().stream().map(role -> roleRepository.findByName(role.getName())).collect(Collectors.toList());
        newMember.setRoles(roles);
        baseRepository.save(newMember);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        logger.info("MemberService: Deleting member with id: " + id);
        Member member = baseRepository.findById(id).orElse(null);
        if (member == null) {
            throw new ResourceNotFoundException();
        }
        baseRepository.delete(member);
    }

    @Override
    public List<PlanDto> findPlansByMemberId(int member_id) throws ResourceNotFoundException {
        if (memberRepository.findById(member_id).isEmpty())
            throw new ResourceNotFoundException();
        List<Plan> plans = memberRepository.findPlansByMemberId(member_id);
        List<PlanDto> planDtos = plans.stream().map(plan -> mapper.map(plan, PlanDto.class)).collect(Collectors.toList());
        return planDtos;
    }

    @Override
    public List<BadgeDto> findBadgesByMemberId(int member_id) throws ResourceNotFoundException{
        if (memberRepository.findById(member_id).isEmpty())
            throw new ResourceNotFoundException();
        List<Badge> badge = memberRepository.findBadgesByMemberId(member_id);
        List<BadgeDto> badgeDto = badge.stream().map(b -> mapper.map(b, BadgeDto.class)).collect(Collectors.toList());
        return badgeDto;
    }

    @Override
    public List<MembershipDto> findMembershipsByMemberId(int member_id) throws ResourceNotFoundException {
        if (memberRepository.findById(member_id).isEmpty())
            throw new ResourceNotFoundException();
        List<Membership> memberships = memberRepository.findMembershipsByMemberId(member_id);
        List<MembershipDto> membershipDtos = memberships.stream().map(membership -> mapper.map(membership, MembershipDto.class)).collect(Collectors.toList());
        return membershipDtos;
    }

    @Override
    public List<BadgeDto> findActiveBadgesByMemberId(int member_id, boolean isActive) throws ResourceNotFoundException {
        if (memberRepository.findById(member_id).isEmpty())
            throw new ResourceNotFoundException();
        List<Badge> badges = memberRepository.findActiveBadgesByMemberId(member_id, true);
        List<BadgeDto> badgeDtos = badges.stream().map(badge -> mapper.map(badge, BadgeDto.class)).collect(Collectors.toList());
        return badgeDtos;
    }

    @Override
    public Collection<TransactionDto> findTransactionsByMemberId(int id) throws ResourceNotFoundException{
        if (memberRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException();
        Collection<Transaction> transactions = memberRepository.findTransactionsByMemberId(id);
        Collection<TransactionDto> transactionDtos = transactions.stream().map(transaction -> mapper.map(transaction, TransactionDto.class)).collect(Collectors.toList());
        return transactionDtos;
    }

}
