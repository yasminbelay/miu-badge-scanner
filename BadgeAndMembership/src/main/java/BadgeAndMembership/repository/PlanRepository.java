package BadgeAndMembership.repository;

import BadgeAndMembership.model.Plan;
import edu.miu.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends BaseRepository <Plan, Integer> {

}
