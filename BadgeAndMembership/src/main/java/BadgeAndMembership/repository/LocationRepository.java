package BadgeAndMembership.repository;

import BadgeAndMembership.model.Location;
import edu.miu.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LocationRepository extends BaseRepository <Location, Integer> {
    Collection<Location> getLocationByPlanId(Integer id);
}