package BadgeAndMembership.service;

import BadgeAndMembership.model.Badge;
import BadgeAndMembership.model.Member;
import BadgeAndMembership.model.Plan;
import contracts.dto.*;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadService;

import java.util.Collection;
import java.util.List;

public interface MemberService extends BaseReadService<MemberDto, Member, Integer> {
    public void add(MemberRequestDto memberDto);
    public void update(int id, MemberRequestDto memberDto) throws ResourceNotFoundException;
    public void delete(int id) throws ResourceNotFoundException;
    public List<PlanDto> findPlansByMemberId(int member_id) throws ResourceNotFoundException;
    public List<BadgeDto> findBadgesByMemberId(int member_id) throws ResourceNotFoundException;
    public List<MembershipDto> findMembershipsByMemberId(int member_id) throws  ResourceNotFoundException;
    public List<BadgeDto> findActiveBadgesByMemberId(int member_id, boolean isActive) throws  ResourceNotFoundException;
    public Collection<TransactionDto> findTransactionsByMemberId(int id) throws ResourceNotFoundException;

}
