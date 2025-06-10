package volunteering.VolunteeringSite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volunteering.VolunteeringSite.model.Vacancy;
import volunteering.VolunteeringSite.repo.VacancyRepository;

@Service
@RequiredArgsConstructor
public class VacancyService implements VacancyServiceIMPL{

    private final VacancyRepository vacancyRepository;

    @Override
    public Vacancy createVacancy(Vacancy vacancy) {
        System.out.println("Saving vacancy: " + vacancy);
        return vacancyRepository.save(vacancy);
        }
}
