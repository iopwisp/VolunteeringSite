package volunteering.VolunteeringSite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import volunteering.VolunteeringSite.model.User;

public interface UserRepository extends JpaRepository<User , Long>{
}
