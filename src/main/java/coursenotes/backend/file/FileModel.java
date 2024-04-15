package coursenotes.backend.file;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.fortuna.ical4j.model.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String fileName;
    LocalDateTime dateCreated;
    LocalDateTime dateModified;
    Boolean isShared;
    String fileUrl;

    public FileModel() {
    }

    public FileModel(Long id, String fileName, LocalDateTime dateCreated, LocalDateTime dateModified, Boolean isShared, String fileUrl) {
        this.id = id;
        this.fileName = fileName;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isShared = isShared;
        this.fileUrl = fileUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileModel fileModel = (FileModel) o;
        return Objects.equals(getId(), fileModel.getId()) && Objects.equals(getFileName(), fileModel.getFileName()) && Objects.equals(getDateCreated(), fileModel.getDateCreated()) && Objects.equals(getDateModified(), fileModel.getDateModified()) && Objects.equals(isShared, fileModel.isShared) && Objects.equals(getFileUrl(), fileModel.getFileUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFileName(), getDateCreated(), getDateModified(), isShared, getFileUrl());
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", isShared=" + isShared +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}

