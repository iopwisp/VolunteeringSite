package volunteering.VolunteeringSite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import volunteering.VolunteeringSite.model.Vacancy;

public interface VacancyRepository extends JpaRepository<Vacancy , Long> , JpaSpecificationExecutor<Vacancy> {
}
