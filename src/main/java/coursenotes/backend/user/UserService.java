package coursenotes.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // get all
    public List<User> getUsers() {
        return userRepository.findAll();
    }

//    // get
//    public UserModel getUser(OAuth2User principal) {
//        int googleId = principal.getAttribute("id");
//        UserModel user = userRepository.findByGoogleId(googleId);
//
//        if (user == null) {
//            UserModel newUser = new UserModel();
//            newUser.setGoogleId(googleId);
//            newUser.setFirstName(principal.getAttribute("name"));
//            newUser.setLastName(principal.getAttribute("family_name"));
//            newUser.setEmail(principal.getAttribute("email"));
//            return userRepository.save(newUser);
//        }
//
//        return user;
//    }

    // create
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // update
    public void updateUser(Long id, User formerUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            formerUser.setFirstName(formerUser.getFirstName());
            formerUser.setLastName(formerUser.getLastName());
            formerUser.setEmail((formerUser.getEmail()));
            userRepository.save(formerUser);
        }
    }

    // delete
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
    }
}
