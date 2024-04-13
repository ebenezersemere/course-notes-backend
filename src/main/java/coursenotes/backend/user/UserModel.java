package coursenotes.backend.user;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(getUserModelId(), userModel.getUserModelId()) && Objects.equals(getLastName(), userModel.getLastName()) && Objects.equals(getFirstName(), userModel.getFirstName()) && Objects.equals(getEmail(), userModel.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserModelId(), getLastName(), getFirstName(), getEmail());
    }
}