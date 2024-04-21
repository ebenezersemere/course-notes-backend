package coursenotes.backend.directory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import coursenotes.backend.folder.Folder;
import coursenotes.backend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("directory")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "directory")
public class Directory extends Folder {
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-directory")
    private User user;
}