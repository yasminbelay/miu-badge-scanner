package BadgeAndMembership.service;

import BadgeAndMembership.model.Location;
import BadgeAndMembership.model.LocationType;
import BadgeAndMembership.model.Member;
import BadgeAndMembership.model.Plan;
import BadgeAndMembership.repository.LocationRepository;
import BadgeAndMembership.repository.PlanRepository;
import contracts.dto.LocationDto;
import contracts.dto.LocationRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@Service
@Transactional
public class LocationServiceImpl extends BaseReadServiceImpl<LocationDto, Location, Integer> implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void add(LocationRequestDto locationDto) throws ResourceNotFoundException {
        Plan plan = planRepository.findById(locationDto.getPlanId()).orElse(null);
        if (plan == null) {
            throw new ResourceNotFoundException();
        }
        Location location = modelMapper.map(locationDto, Location.class);
        location.setPlan(plan);
        locationRepository.save(location);
    }

    public void update(Integer id, LocationRequestDto locationDto) throws ResourceNotFoundException {
        Location location = baseRepository.findById(id).orElse(null);
        if (location == null) {
            throw new ResourceNotFoundException();
        }

        Plan plan = planRepository.findById(locationDto.getPlanId()).orElse(null);
        if (plan == null) {
            throw new ResourceNotFoundException();
        }

        Location newLocation = modelMapper.map(locationDto, Location.class);
        newLocation.setPlan(plan);
        newLocation.setId(id);
        baseRepository.save(newLocation);
    }

    @Override
    public void deleteById(Integer id) {
        baseRepository.deleteById(id);
    }

    @Override
    public void getById(Integer id) {
        baseRepository.findById(id);
    }

    @Override
    public void getAll() {
        baseRepository.findAll();
    }


}

