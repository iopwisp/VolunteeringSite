package volunteering.VolunteeringSite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volunteering.VolunteeringSite.model.User;
import volunteering.VolunteeringSite.model.Vacancy;
import volunteering.VolunteeringSite.repo.UserRepository;
import volunteering.VolunteeringSite.repo.VacancyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowApplicants {

    private final VacancyRepository vacancyRepository;

    public List<User> showApplicants(Long vacancyId){
        Vacancy vacancy = vacancyRepository.findById(vacancyId).
                orElseThrow(() -> new RuntimeException("Vacancy not found"));
        return vacancy.getApplicants();
    }
}
