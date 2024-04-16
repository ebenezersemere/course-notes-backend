package coursenotes.backend.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    String fileName;
    LocalDateTime dateCreated;
    LocalDateTime dateModified;
    Boolean isShared;
    String fileUrl;

    public File() {
    }

    public File(UUID id, String fileName, LocalDateTime dateCreated, LocalDateTime dateModified, Boolean isShared, String fileUrl) {
        this.id = id;
        this.fileName = fileName;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isShared = isShared;
        this.fileUrl = fileUrl;
    }
}

