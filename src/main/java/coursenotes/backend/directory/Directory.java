package coursenotes.backend.directory;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import coursenotes.backend.folder.Folder;
import coursenotes.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonTypeName("directory")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "directory")
public class Directory extends Folder {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}