package coursenotes.backend.directory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coursenotes.backend.course.Course;
import coursenotes.backend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "directory")
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "directory_id")
    private UUID directoryId;

    @Column(name = "directory_name")
    private String directoryName;

    // a directory may be a child of another directory
    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory parentDirectory;

    // a directory may have many child directories
    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Directory> childDirectories;

    // a directory has one owner
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

}
