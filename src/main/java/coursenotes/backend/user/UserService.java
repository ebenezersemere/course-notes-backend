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
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    // get
    public UserModel getUser(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // http://localhost:8080/swagger-ui/index.html/

    // create
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    // update

    public void updateUser(Long id, UserModel user) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserModel formerUser = getUser(id);
            formerUser.setFirstName(user.getFirstName());
            formerUser.setLastName(user.getLastName());
            formerUser.setEmail((user.getEmail()));
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
