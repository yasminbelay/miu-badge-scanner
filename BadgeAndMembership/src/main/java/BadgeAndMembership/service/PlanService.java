package BadgeAndMembership.service;


import BadgeAndMembership.model.Location;
import BadgeAndMembership.model.Plan;
import contracts.dto.LocationDto;
import contracts.dto.PlanDto;
import contracts.dto.PlanRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadService;

import java.util.Collection;

public interface PlanService extends BaseReadService<PlanDto, Plan, Integer> {
    public void add(PlanRequestDto planDto);

    public void update(Integer id, PlanRequestDto planDto) throws ResourceNotFoundException;

    public void deleteById(Integer id);

    public Collection<LocationDto> getAllLocations(Integer id) throws ResourceNotFoundException;
}



