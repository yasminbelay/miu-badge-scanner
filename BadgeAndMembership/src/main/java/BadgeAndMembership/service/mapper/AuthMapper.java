package BadgeAndMembership.service.mapper;

import BadgeAndMembership.model.Member;
import contracts.dto.auth.AuthResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper extends BaseMapper<Member, AuthResponse> {
    public AuthMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Member.class, AuthResponse.class);
    }
}