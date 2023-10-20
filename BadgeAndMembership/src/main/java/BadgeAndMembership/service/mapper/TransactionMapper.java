package BadgeAndMembership.service.mapper;

import BadgeAndMembership.model.Transaction;
import contracts.dto.TransactionDto;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper extends BaseMapper<Transaction, TransactionDto> {
    public TransactionMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Transaction.class, TransactionDto.class);
    }
}
