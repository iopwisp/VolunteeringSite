package volunteering.VolunteeringSite.model;

import lombok.Getter;
import lombok.Setter;
import volunteering.VolunteeringSite.repo.VacancyStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class VacancyDTO {
    private String title;
    private String description;
    private String address;
    private LocalDateTime dateTime;
    private LocalDateTime endDate;
    private VacancyStatus status;
}
