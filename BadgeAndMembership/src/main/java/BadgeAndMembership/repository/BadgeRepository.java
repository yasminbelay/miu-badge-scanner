package BadgeAndMembership.repository;

import BadgeAndMembership.model.Badge;
import edu.miu.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BadgeRepository extends BaseRepository<Badge, Integer> {
    @Modifying
    @Query("update Badge b set b.isActive = :isActive, b.modifiedDate = :modifiedDate where b.member.id = :memberId")
    public void updateBadgeStatusByMemberId(@Param("memberId") int memberId, @Param("isActive") boolean isActive, @Param("modifiedDate")LocalDateTime modifiedDate);

    Optional<Badge> findByBadgeUid(String badgeUid);
}
