package coursenotes.backend.course;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import coursenotes.backend.folder.Folder;
import coursenotes.backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("course")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();
}