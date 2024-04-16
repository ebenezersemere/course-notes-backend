package coursenotes.backend.directory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coursenotes.backend.course.Course;
import coursenotes.backend.file.File;
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

    // RELATIONSHIPS
    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Directory> childDirectories;

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files;

    @ManyToOne
    @JoinColumn(name = "parent_directory_id")
    private Directory parentDirectory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
