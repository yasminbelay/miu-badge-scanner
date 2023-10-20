package BadgeAndMembership.service;

import BadgeAndMembership.model.Transaction;
import contracts.dto.TransactionDto;
import contracts.dto.TransactionRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadService;

import java.util.List;
import java.util.Optional;

public interface TransactionService extends BaseReadService<TransactionDto, Transaction, Integer> {
//    public void add(LocalDateTime dateTime, BadgeDto badgeDto, LocationDto locationDto, TransactionStatus transactionStatus);

    public TransactionDto add(TransactionRequestDto transactionDto) throws ResourceNotFoundException;

    public void update(Integer id, TransactionDto transactionDto) throws ResourceNotFoundException;

    public void deleteById(Integer id);

//    public void getById(Integer id);
//    public void getAll();

//    public List<TransactionDto> findAll();
//    public Optional<TransactionDto> findById(int id);

}
