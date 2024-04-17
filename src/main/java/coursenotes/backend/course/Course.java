package coursenotes.backend.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coursenotes.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseId;

    @Column(name = "course_uid")
    private String courseUID;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "course_location")
    private String courseLocation;

    private enum courseSemester {
        Spring2024
    }

    @Column(name = "course_semester")
    @Enumerated(EnumType.STRING)
    private courseSemester courseSemester;

    // RELATIONSHIPS
    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}
