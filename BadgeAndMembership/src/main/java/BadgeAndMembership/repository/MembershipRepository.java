package BadgeAndMembership.repository;

import BadgeAndMembership.model.Membership;
import BadgeAndMembership.model.Plan;
import edu.miu.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Collection;

public interface MembershipRepository extends BaseRepository<Membership, Integer> {

    Collection<Membership> findAllByMember_Id(int memberId);

}
