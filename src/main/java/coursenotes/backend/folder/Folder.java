package coursenotes.backend.folder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coursenotes.backend.file.File;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "folder")
public abstract class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "folder_id")
    private UUID folderId;

    @Column(name = "folder_name")
    private String folderName;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files;

    @OneToMany(mappedBy = "parentFolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Folder> childFolders;

    @ManyToOne
    @JoinColumn(name = "parent_folder_id")
    private Folder parentFolder;
}