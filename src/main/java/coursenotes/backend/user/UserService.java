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
//
//            if (user.getFiles() != null) {
//                updatedUser.getFiles().clear();
//                updatedUser.getFiles().addAll(user.getFiles());
//            }
//
            updatedUser.setFileIds(user.getFileIds());

            if (user.getDirectories() != null) {
                updatedUser.getDirectories().clear();
                updatedUser.getDirectories().addAll(user.getDirectories());
            }

//            if (user.getCourses() != null) {
//                updatedUser.getCourses().clear();
//                updatedUser.getCourses().addAll(user.getCourses());
//            }

            userRepository.save(updatedUser);
        }
    }

    public void deleteUser(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
        }
    }

//    // FILES
//    public void addFile(UUID userId, UUID fileId) {
//        System.out.println("Adding file to user");
//
//        User user = readUser(userId);
//        if (user == null)
//            return;
//        File file = fileService.readFile(fileId);
//        if (file == null)
//            return;
//
//        user.getFiles().add(file);
//        file.setUser(user);
//
//        userRepository.save(user);
//    }
//
//    public void removeFile(UUID userId, UUID fileId) {
//        User user = readUser(userId);
//        if (user == null)
//            return;
//        File file = fileService.readFile(fileId);
//        if (file == null)
//            return;
//
//        user.getFiles().remove(file);
//        file.setUser(user);
//
//        userRepository.save(user);
//    }
//
//    public void addDirectory(UUID userId, UUID directoryId) {
//        User user = readUser(userId);
//        if (user == null)
//            return;
//        Directory directory = directoryService.readDirectory(directoryId);
//        if (directory == null)
//            return;
//
//        user.getDirectories().add(directory);
//        directory.setUser(user);
//
//        userRepository.save(user);
//    }
//
//    public void removeDirectory(UUID userId, UUID directoryId) {
//        User user = readUser(userId);
//        if (user == null)
//            return;
//        Directory directory = directoryService.readDirectory(directoryId);
//        if (directory == null)
//            return;
//
//        user.getDirectories().remove(directory);
//        directory.setUser(user);
//
//        userRepository.save(user);
//    }
//
//    public void addCourse(UUID userId, UUID courseId) {
//        User user = readUser(userId);
//        if (user == null)
//            return;
//        Course course = courseService.readCourse(courseId);
//        if (course == null)
//            return;
//
//        user.getCourses().add(course);
//        course.getUsers().add(user);
//        userRepository.save(user);
//    }
//
//    public void removeCourse(UUID userId, UUID courseId) {
//        User user = readUser(userId);
//        if (user == null)
//            return;
//        Course course = courseService.readCourse(courseId);
//        if (course == null)
//            return;
//
//        user.getCourses().remove(course);
//        course.getUsers().remove(user);
//        userRepository.save(user);
//    }
}
