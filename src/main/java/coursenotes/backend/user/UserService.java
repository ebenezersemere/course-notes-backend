package coursenotes.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User readUser(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void updateUser(UUID userId, User formerUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            formerUser.setFirstName(formerUser.getFirstName());
            formerUser.setLastName(formerUser.getLastName());
            formerUser.setEmail((formerUser.getEmail()));
            userRepository.save(formerUser);
        }
    }

    public void deleteUser(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
        }
    }
}
