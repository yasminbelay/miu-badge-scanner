package BadgeAndMembership.repository;

import BadgeAndMembership.model.Transaction;
import edu.miu.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction,Integer> {
    @Query("select t from Transaction t where t.membership.id = :membershipId and t.status = 'ALLOWED'")
    List<Transaction> findAllAllowedByMembership_Id(int membershipId);
    @Query("select count(t) from Transaction t where t.status = 'ALLOWED' and t.datetime between :startDateTime and :endDateTime")
    int countAllowedByDatetimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    @Query("select count(t) from Transaction t where t.membership.id = :membershipId and t.status = 'ALLOWED' and t.datetime between :startDateTime and :endDateTime")
    int countAllowedByMembership_IdAndDatetimeBetween(int membershipId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
