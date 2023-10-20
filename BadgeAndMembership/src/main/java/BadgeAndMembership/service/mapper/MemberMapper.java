package BadgeAndMembership.service.mapper;

import BadgeAndMembership.model.Member;
import contracts.dto.MemberDto;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper extends BaseMapper<Member, MemberDto> {
    public MemberMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Member.class, MemberDto.class);
    }
}
