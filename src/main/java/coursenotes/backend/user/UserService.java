package coursenotes.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // get all
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    // get
    public UserModel getUser(OAuth2User principal) {
        int googleId = principal.getAttribute("id");
        UserModel user = userRepository.findByGoogleId(googleId);

        if (user == null) {
            UserModel newUser = new UserModel();
            newUser.setGoogleId(googleId);
            newUser.setFirstName(principal.getAttribute("name"));
            newUser.setLastName(principal.getAttribute("family_name"));
            newUser.setEmail(principal.getAttribute("email"));
            return userRepository.save(newUser);
        }

        return user;
    }

    // create
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    // update
    public void updateUser(Long id, UserModel formerUser) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            formerUser.setFirstName(formerUser.getFirstName());
            formerUser.setLastName(formerUser.getLastName());
            formerUser.setEmail((formerUser.getEmail()));
            userRepository.save(formerUser);
        }
    }

    // delete
    public void deleteUser(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
    }
}
