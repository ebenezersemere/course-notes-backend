package coursenotes.backend.directory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coursenotes.backend.user.UserModel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "directorymodel")
public class DirectoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DirectoryModelId;

    private String Name;
    private Integer Parent;
    private Integer Course;

    @ManyToOne
    @JoinColumn(name="UserModelId", referencedColumnName="UserModelId")
    private UserModel uerModel;

    public DirectoryModel() {

    }

    public DirectoryModel(Long DirectoryModelId, String Name, Integer Parent, Integer Course) {
        this.DirectoryModelId = DirectoryModelId;
        this.Name = Name;
        this.Parent = Parent;
        this.Course = Course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectoryModel)) return false;
        DirectoryModel directroyModel = (DirectoryModel) o;
        return Objects.equals(getDirectoryModelId(), directoryModel.getDirectoryModelId()) && Objects.equals(getName(), directoryModel.getName()) && Objects.equals(getParent(), directoryModel.getParent()) && Objects.equals(getCourse(), directoryModel.getCourse());
    }



}
