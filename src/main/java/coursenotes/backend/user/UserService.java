package coursenotes.backend.user;

import coursenotes.backend.course.Course;
import coursenotes.backend.course.CourseService;
import coursenotes.backend.directory.Directory;
import coursenotes.backend.directory.DirectoryService;
import coursenotes.backend.file.File;
import coursenotes.backend.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

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

    public void updateUser(UUID userId, User user) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User updatedUser = userOptional.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setFileIds(user.getFileIds());

            if (user.getDirectories() != null) {
                updatedUser.getDirectories().clear();
                updatedUser.getDirectories().addAll(user.getDirectories());
            }
            userRepository.save(updatedUser);
        }
    }

    public void deleteUser(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
        }
    }
}
