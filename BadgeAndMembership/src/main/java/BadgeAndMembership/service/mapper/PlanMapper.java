package BadgeAndMembership.service.mapper;

import BadgeAndMembership.model.Plan;
import contracts.dto.PlanDto;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper  extends BaseMapper <Plan, PlanDto> {
    public PlanMapper(MapperFactory mapperFactory){super(mapperFactory, Plan.class, PlanDto.class);}
}