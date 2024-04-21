package coursenotes.backend.directory;

import coursenotes.backend.course.Course;
import coursenotes.backend.course.CourseRepository;
import coursenotes.backend.file.File;
import coursenotes.backend.file.FileService;
import coursenotes.backend.user.User;
import coursenotes.backend.user.UserRepository;
import coursenotes.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DirectoryService {
    private final DirectoryRepository directoryRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public DirectoryService(DirectoryRepository directoryRepository, UserService userService, UserRepository userRepository, CourseRepository courseRepository) {
        this.directoryRepository = directoryRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public Directory createDirectory(Directory directory) {
        return directoryRepository.save(directory);
    }

    public Directory readDirectory(UUID directoryId) {
        return directoryRepository.findById(directoryId).orElse(null);
    }

    public void updateDirectory(UUID directoryId, Directory directory) {
        Optional<Directory> directoryOptional = directoryRepository.findById(directoryId);
        if (directoryOptional.isPresent()) {
            Directory updatedDirectory = directoryOptional.get();
            updatedDirectory.setFolderName(directory.getFolderName());
            updatedDirectory.setFiles(directory.getFiles());
            updatedDirectory.setChildFolders(directory.getChildFolders());
            updatedDirectory.setParentFolder(directory.getParentFolder());
            updatedDirectory.setUser(directory.getUser());
            directoryRepository.save(updatedDirectory);
        }
    }

    public void deleteDirectory(UUID directoryId) {
        Optional<Directory> directoryOptional = directoryRepository.findById(directoryId);
        if (directoryOptional.isPresent()) {
            directoryRepository.deleteById(directoryId);
        }
    }

    public void addDirectoryToUser(UUID userId, UUID directoryId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            return;
        Directory directory = readDirectory(directoryId);
        if (directory == null)
            return;
        user.getDirectories().add(directory);
        directory.setUser(user);
        userRepository.save(user);
    }

    public void removeDirectoryFromUser(UUID userId, UUID directoryId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            return;
        Directory directory = readDirectory(directoryId);
        if (directory == null)
            return;
        user.getDirectories().remove(directory);
        directory.setUser(user);
        userRepository.save(user);
    }

    public void addDirectoryToDirectory(UUID parentDirectoryId, UUID childDirectoryId) {
        Directory parentDirectory = readDirectory(parentDirectoryId);
        if (parentDirectory == null)
            return;
        Directory childDirectory = readDirectory(childDirectoryId);
        if (childDirectory == null)
            return;
        parentDirectory.getChildFolders().add(childDirectory);
        childDirectory.setParentFolder(parentDirectory);
        directoryRepository.save(parentDirectory);
    }

    public void removeDirectoryFromDirectory(UUID parentDirectoryId, UUID childDirectoryId) {
        Directory parentDirectory = readDirectory(parentDirectoryId);
        if (parentDirectory == null)
            return;
        Directory childDirectory = readDirectory(childDirectoryId);
        if (childDirectory == null)
            return;
        parentDirectory.getChildFolders().remove(childDirectory);
        childDirectory.setParentFolder(null);
        directoryRepository.save(parentDirectory);
    }

    public void addDirectoryToCourse(UUID courseId, UUID directoryId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            return;
        Directory directory = readDirectory(directoryId);
        if (directory == null)
            return;
        course.getChildFolders().add(directory);
        directory.setParentFolder(null);
        courseRepository.save(course);
    }

    public void removeDirectoryFromCourse(UUID courseId, UUID directoryId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            return;
        Directory directory = readDirectory(directoryId);
        if (directory == null)
            return;
        course.getChildFolders().remove(directory);
        directory.setParentFolder(null);
        courseRepository.save(course);
    }
}
