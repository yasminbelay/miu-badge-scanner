package BadgeAndMembership.repository;

import BadgeAndMembership.model.Role;
import edu.miu.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Integer> {
    Role findByName(String name);
}
