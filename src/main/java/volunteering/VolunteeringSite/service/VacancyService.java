package volunteering.VolunteeringSite.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volunteering.VolunteeringSite.model.Vacancy;
import volunteering.VolunteeringSite.repo.VacancyRepository;
import volunteering.VolunteeringSite.repo.VacancyStatus;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService{

    private final VacancyRepository vacancyRepository;

    public Vacancy createVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    public List<Vacancy> getFiltered(String status, String title) {
        return vacancyRepository.findAll((root, query, cb) ->
        {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null && !status.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), VacancyStatus.valueOf(status)));
            }

            if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

}
