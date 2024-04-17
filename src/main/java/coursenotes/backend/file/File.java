package coursenotes.backend.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coursenotes.backend.directory.Directory;
import coursenotes.backend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @Column(name = "is_shared")
    private Boolean isShared;

    @Column(name = "file_url")
    private String fileUrl;

    // RELATIONSHIPS
    @ManyToMany
    @JoinTable(
            name = "user_file",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}

