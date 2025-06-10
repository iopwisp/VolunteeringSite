package volunteering.VolunteeringSite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volunteering.VolunteeringSite.model.Vacancy;
import volunteering.VolunteeringSite.repo.VacancyRepository;

@Service
@RequiredArgsConstructor
public class DeleteVacancy {

    private final VacancyRepository vacancyRepository;

    public void deleteVacancy(Long id){
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        vacancyRepository.delete(vacancy);
    }
}
