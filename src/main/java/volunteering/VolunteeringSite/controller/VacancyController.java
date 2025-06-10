package volunteering.VolunteeringSite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volunteering.VolunteeringSite.model.User;
import volunteering.VolunteeringSite.model.Vacancy;
import volunteering.VolunteeringSite.model.VacancyDTO;
import volunteering.VolunteeringSite.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final DeleteVacancy deleteVacancy;
    private final ShowApplicants showApplicants;
    private final ApplyOnVacancy applyOnVacancy;
    private final EditVacancy editVacancy;

    @PostMapping("/create")
    public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy){
        Vacancy saved = vacancyService.createVacancy(vacancy);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vacancy> deletedVacancy(@PathVariable Long id){
        deleteVacancy.deleteVacancy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/applicants")
    public ResponseEntity<List<User>> showApplicants(@PathVariable Long id){
        List<User> applicants = showApplicants.showApplicants(id);
        return ResponseEntity.ok(applicants);
    }

    @PostMapping("/{vacancyId}/apply/{userId}")
    public ResponseEntity<String> applyOnVacancy(@PathVariable Long userId,
                                                  @PathVariable Long vacancyId)
    {
        applyOnVacancy.applyOnVacancies(userId , vacancyId);
        return ResponseEntity.ok("User applied");
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<String> editVacancy(@PathVariable Long id , @RequestBody VacancyDTO dto){
        editVacancy.editVacancy(id , dto);
        return ResponseEntity.ok("Vacancy updated");
    }
}
