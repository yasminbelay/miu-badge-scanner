package BadgeAndMembership.service;

import BadgeAndMembership.model.Membership;
import contracts.dto.MembershipDto;
import contracts.dto.MembershipRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadService;

public interface MembershipService extends BaseReadService<MembershipDto, Membership, Integer> {
    public void add(MembershipRequestDto membershipRequestDto) throws ResourceNotFoundException;
    public void update(int id, MembershipRequestDto membershipRequestDto) throws ResourceNotFoundException;
    public void delete(int id) throws ResourceNotFoundException;
}
