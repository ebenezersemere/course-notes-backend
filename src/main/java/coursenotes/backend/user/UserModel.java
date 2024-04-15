package coursenotes.backend.user;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "usermodel")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserModelId;

    private String lastName;
    private String firstName;
    private String email;

    // OAuth2User principal.getAttribute("id")
    private int googleId;

    public UserModel() {
    }

    public UserModel(Long userModelId, String lastName, String firstName, String email, int googleId) {
        UserModelId = userModelId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.googleId = googleId;
    }

    public Long getUserModelId() {
        return UserModelId;
    }

    public void setUserModelId(Long userModelId) {
        UserModelId = userModelId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGoogleId() {
        return googleId;
    }

    public void setGoogleId(int googleId) {
        this.googleId = googleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return getGoogleId() == userModel.getGoogleId() && Objects.equals(getUserModelId(), userModel.getUserModelId()) && Objects.equals(getLastName(), userModel.getLastName()) && Objects.equals(getFirstName(), userModel.getFirstName()) && Objects.equals(getEmail(), userModel.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserModelId(), getLastName(), getFirstName(), getEmail(), getGoogleId());
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "UserModelId=" + UserModelId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", googleId=" + googleId +
                '}';
    }
}