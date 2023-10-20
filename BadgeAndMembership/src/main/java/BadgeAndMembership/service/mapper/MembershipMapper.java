package BadgeAndMembership.service.mapper;

import BadgeAndMembership.model.Membership;
import contracts.dto.MembershipDto;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper extends BaseMapper<Membership, MembershipDto> {
    public MembershipMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Membership.class, MembershipDto.class);
    }

}
