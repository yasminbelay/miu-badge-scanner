package BadgeAndMembership.repository;

import BadgeAndMembership.model.*;
import edu.miu.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends BaseRepository<Member, Integer> {

    @Query("Select b from Badge b join b.member m where m.id=:member_id")
    List<Badge> findBadgesByMemberId(@Param("member_id") int member_id);

    @Query("SELECT ms.plan FROM Membership ms join ms.member m WHERE m.id = :member_id")
    List<Plan> findPlansByMemberId(@Param("member_id") int member_id);

    @Query("SELECT ms FROM Membership ms join ms.member m WHERE m.id = :member_id")
    List<Membership> findMembershipsByMemberId(@Param("member_id") int member_id);

    @Query("Select b from Badge b join b.member m where m.id=:member_id and b.isActive = :isActive")
    List<Badge> findActiveBadgesByMemberId(@Param("member_id") int member_id, @Param("isActive") boolean isActive);

    Optional<Member> findByEmail(String email);

    @Query("select t from Transaction t where t.badge.member.id = :id")
    Collection<Transaction> findTransactionsByMemberId(@Param("id") int id);
}
