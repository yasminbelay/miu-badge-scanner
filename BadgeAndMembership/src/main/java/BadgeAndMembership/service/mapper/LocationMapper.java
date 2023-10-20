package BadgeAndMembership.service.mapper;

import BadgeAndMembership.model.Location;
import contracts.dto.LocationDto;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper extends BaseMapper <Location, LocationDto> {
    public LocationMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Location.class, LocationDto.class);
    }
}
