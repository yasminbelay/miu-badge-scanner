package BadgeAndMembership.service;


import BadgeAndMembership.model.Location;
import contracts.dto.LocationDto;
import contracts.dto.LocationRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadService;

public interface LocationService extends BaseReadService<LocationDto, Location, Integer> {
    public void add(LocationRequestDto locationDto) throws ResourceNotFoundException;
    public void update(Integer id, LocationRequestDto locationDto ) throws ResourceNotFoundException;

    public void deleteById ( Integer id );
     public void getById(Integer id);
      public void getAll();

}
