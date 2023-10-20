package BadgeAndMembership.service;

import BadgeAndMembership.model.Badge;
import contracts.dto.BadgeDto;
import contracts.dto.BadgeRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadService;

public interface BadgeService extends BaseReadService<BadgeDto, Badge, Integer> {
    public void addBadge(BadgeRequestDto badge) throws ResourceNotFoundException;
    public void updateBadge(int id, BadgeRequestDto requestDto) throws ResourceNotFoundException;
    public void deleteBadge(Integer id);
}
