package coursenotes.backend.course;

import coursenotes.backend.folder.Folder;
import coursenotes.backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "course")
public class Course extends Folder {
    @Column(name = "course_uid")
    private String courseUID;

    @Column(name = "course_description", columnDefinition = "TEXT")
    private String courseDescription;

    @Column(name = "course_location")
    private String courseLocation;

    enum courseSemester {
        Spring2024,
        Fall2024
    }

    @Column(name = "course_semester")
    @Enumerated(EnumType.STRING)
    private courseSemester courseSemester;

    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}