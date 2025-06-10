package volunteering.VolunteeringSite.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volunteering.VolunteeringSite.model.Vacancy;
import volunteering.VolunteeringSite.model.VacancyDTO;
import volunteering.VolunteeringSite.repo.VacancyRepository;

@Service
@RequiredArgsConstructor
public class EditVacancy {

    private final VacancyRepository vacancyRepository;

    @Transactional
    public void editVacancy(Long id, VacancyDTO dto) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        if (dto.getTitle() != null) vacancy.setTitle(dto.getTitle());
        if (dto.getAddress() != null) vacancy.setAddress(dto.getAddress());
        if (dto.getDescription() != null) vacancy.setDescription(dto.getDescription());
        if (dto.getStatus() != null) vacancy.setStatus(dto.getStatus());

        vacancyRepository.save(vacancy);
    }
}
