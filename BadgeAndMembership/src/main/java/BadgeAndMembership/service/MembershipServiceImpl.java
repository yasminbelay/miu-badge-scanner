package BadgeAndMembership.service;

import BadgeAndMembership.model.Member;
import BadgeAndMembership.model.Membership;
import BadgeAndMembership.model.Plan;
import BadgeAndMembership.repository.MemberRepository;
import BadgeAndMembership.repository.MembershipRepository;
import BadgeAndMembership.repository.PlanRepository;
import contracts.dto.MemberDto;
import contracts.dto.MembershipDto;
import contracts.dto.MembershipRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.repository.BaseRepository;
import edu.miu.common.service.BaseReadServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class MembershipServiceImpl extends BaseReadServiceImpl<MembershipDto, Membership, Integer> implements MembershipService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private Logger logger;

    @Override
    public void add(MembershipRequestDto membershipRequestDto) throws ResourceNotFoundException {
        Membership membership = modelMapper.map(membershipRequestDto, Membership.class);
        Member member = memberRepository.findById(membershipRequestDto.getMemberId()).orElse(null);
        if (member == null)
            throw new ResourceNotFoundException();
        Plan plan = planRepository.findById(membershipRequestDto.getPlanId()).orElse(null);
        if (plan == null)
            throw new ResourceNotFoundException();
        membership.setPlan(plan);
        membership.setMember(member);
        membershipRepository.save(membership);
        logger.info("MembershipService: Successfully Added Membership: " + membership);
    }

    @Override
    public void update(int id, MembershipRequestDto membershipRequestDto) throws ResourceNotFoundException {
        Membership membership = modelMapper.map(membershipRequestDto, Membership.class);
        if(membershipRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException();
        Member member = memberRepository.findById(membershipRequestDto.getMemberId()).orElse(null);
        if (member == null)
            throw new ResourceNotFoundException();
        Plan plan = planRepository.findById(membershipRequestDto.getPlanId()).orElse(null);
        if (plan == null)
            throw new ResourceNotFoundException();
        membership.setId(id);
        membership.setPlan(plan);
        membership.setMember(member);
        membershipRepository.save(membership);
        logger.info("MembershipService: Successfully Updated Membership: " + membership);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        if (membershipRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException();
        logger.info("MembershipService: Successfully Deleted Membership with ID: " + id);
        membershipRepository.deleteById(id);
    }
}
