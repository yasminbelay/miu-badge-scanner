package BadgeAndMembership.service;

import BadgeAndMembership.model.Location;
import BadgeAndMembership.model.Plan;
import BadgeAndMembership.model.Role;
import BadgeAndMembership.repository.LocationRepository;
import BadgeAndMembership.repository.MemberRepository;
import BadgeAndMembership.repository.PlanRepository;
import BadgeAndMembership.repository.RoleRepository;
import contracts.dto.LocationDto;
import contracts.dto.PlanDto;
import contracts.dto.PlanRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanServiceImpl extends BaseReadServiceImpl<PlanDto, Plan, Integer> implements PlanService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void add(PlanRequestDto planDto) {
        Plan plan = modelMapper.map(planDto,Plan.class);
        Collection<Role> roles = planDto.getAllowedRoles().stream().map(role -> roleRepository.findByName(role.getName())).collect(Collectors.toList());
        plan.setAllowedRoles(roles);
        planRepository.save(plan);
    }

    @Override
    public void update(Integer id, PlanRequestDto planDto) throws ResourceNotFoundException {
        if(planRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException();
        }
        Plan plan = modelMapper.map(planDto,Plan.class);
        plan.setId(id);
        Collection<Role> roles = planDto.getAllowedRoles().stream().map(role -> roleRepository.findByName(role.getName())).collect(Collectors.toList());
        plan.setAllowedRoles(roles);
        planRepository.save(plan);
    }

    @Override
    public void deleteById(Integer id) {
        planRepository.deleteById(id);
    }

    //to get all locations for a plan
    @Override
    public Collection<LocationDto> getAllLocations(Integer id) throws ResourceNotFoundException {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan == null)
            throw new ResourceNotFoundException();
        Collection<Location> locations = locationRepository.getLocationByPlanId(id);
        Collection<LocationDto> locationDtos = locations.stream().map(location -> modelMapper.map(location, LocationDto.class)).collect(Collectors.toList());
        return locationDtos;
    }
}