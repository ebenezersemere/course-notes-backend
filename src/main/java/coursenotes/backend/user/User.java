package coursenotes.backend.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID UserModelId;

    private String lastName;
    private String firstName;
    private String email;

    // OAuth2User principal.getAttribute("id")
    private int googleId;

    public User() {
    }

    public User(UUID userModelId, String lastName, String firstName, String email, int googleId) {
        UserModelId = userModelId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.googleId = googleId;
    }

}