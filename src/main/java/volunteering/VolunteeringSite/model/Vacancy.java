package volunteering.VolunteeringSite.model;

import jakarta.persistence.*;
import lombok.*;
import volunteering.VolunteeringSite.repo.VacancyStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vacancies", schema = "volunteer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String address;

    private LocalDateTime dateTime;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Enumerated(EnumType.STRING)
    private VacancyStatus status;

    @Column(name = "end_date", unique = true)
    private LocalDateTime endDate;

    @ManyToMany
    @JoinTable(
            name = "vacancy_applications",
            schema = "volunteer",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> applicants = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = VacancyStatus.WILL_BE_OPENED;
    }
}