package BadgeAndMembership.controller;

import BadgeAndMembership.model.Transaction;
import BadgeAndMembership.service.TransactionService;
import contracts.dto.TransactionDto;
import contracts.dto.TransactionRequestDto;
import edu.miu.common.controller.BaseReadController;
import edu.miu.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;


@RestController
@RequestMapping("/transactions")
public class TransactionController extends BaseReadController<TransactionDto, Transaction, Integer> {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TransactionRequestDto transactionRequestDto) throws ResourceNotFoundException {
        logger.info("TransactionController: Adding transaction :{}" + transactionRequestDto);
        TransactionDto transactionDto = transactionService.add(transactionRequestDto);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody TransactionDto transactionDto) throws ResourceNotFoundException {
        logger.info("TransactionService: Updating transaction with id: " + id + " to: {}" + transactionDto);
        transactionService.update(id, transactionDto);
        return new ResponseEntity<>("Transaction updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id) throws ResourceNotFoundException {
        logger.info("TransactionService: Deleting transaction with id: " + id);
        transactionService.deleteById(id);
        return new ResponseEntity<>("Transaction deleted successfully", HttpStatus.OK);
    }


}
