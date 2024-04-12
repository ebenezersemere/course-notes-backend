package coursenotes.backend.user;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserModelId;

    private String lastName;
    private String firstName;
    private String email;

    public UserModel() {

    }

    public UserModel(Long UserModelId, String lastName, String firstName, String email) {
        this.UserModelId = UserModelId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }
}
