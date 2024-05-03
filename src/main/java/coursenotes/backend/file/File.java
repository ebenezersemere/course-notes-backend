package coursenotes.backend.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coursenotes.backend.folder.Folder;
import coursenotes.backend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
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

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @JsonBackReference("folder-file")
    private Folder folder;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-file")
    private User user;

    @Column(name="user_string")
    private String userString;
}

