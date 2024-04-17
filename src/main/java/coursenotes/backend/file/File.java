package coursenotes.backend.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coursenotes.backend.folder.Folder;
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

    @Column(name = "file_body", columnDefinition = "TEXT")
    private String fileBody;

//    @Column(name = "file_url")
//    private String fileUrl;

    // RELATIONSHIPS
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_file",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}

