package coursenotes.backend.folder;

import com.fasterxml.jackson.annotation.*;
import coursenotes.backend.course.Course;
import coursenotes.backend.directory.Directory;
import coursenotes.backend.file.File;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "folder_id")
    private UUID folderId;

    @Column(name = "folder_name")
    private String folderName;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("folder-file")
    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "parentFolder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("folder-folder")
    private List<Folder> childFolders = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "parent_folder_id")
    @JsonBackReference("folder-folder")
    private Folder parentFolder;
}