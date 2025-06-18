package volunteering.VolunteeringSite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import volunteering.VolunteeringSite.kafka.ExKafkaProducer;
import volunteering.VolunteeringSite.model.User;
import volunteering.VolunteeringSite.model.Vacancy;
import volunteering.VolunteeringSite.repo.UserRepository;
import volunteering.VolunteeringSite.repo.VacancyRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplyOnVacancy {

    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;
    private final ExKafkaProducer kafkaProducer;

    public void applyOnVacancies(Long userId, Long vacancyId) {
        log.info("user {} is applying to vacancy {}" , userId , vacancyId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        vacancy.getApplicants().add(user);
        vacancyRepository.save(vacancy);
        kafkaProducer.sendMessage("order-events" , "Order created:" + vacancyId);
        log.info("user {} successfully applied to vacancy {}" , userId , vacancyId);
    }
}
