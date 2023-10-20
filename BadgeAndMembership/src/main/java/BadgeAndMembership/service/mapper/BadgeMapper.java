package BadgeAndMembership.service.mapper;

import BadgeAndMembership.model.Badge;
import contracts.dto.BadgeDto;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class BadgeMapper extends BaseMapper<Badge, BadgeDto> {
    public BadgeMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Badge.class, BadgeDto.class);
    }

}
