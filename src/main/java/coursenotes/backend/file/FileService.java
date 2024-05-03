package coursenotes.backend.file;

import coursenotes.backend.course.Course;
import coursenotes.backend.course.CourseRepository;
import coursenotes.backend.directory.Directory;
import coursenotes.backend.directory.DirectoryRepository;
import coursenotes.backend.directory.DirectoryService;
import coursenotes.backend.folder.Folder;
import coursenotes.backend.user.User;
import coursenotes.backend.user.UserRepository;
import coursenotes.backend.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final DirectoryRepository directoryRepository;
    private final CourseRepository courseRepository;

    public FileService(FileRepository fileRepository, UserRepository userRepository, DirectoryRepository directoryRepository, CourseRepository courseRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.directoryRepository = directoryRepository;
        this.courseRepository = courseRepository;
    }

    public File createFile(File file) {
        return fileRepository.save(file);
    }

    public File readFile(UUID fileId) {
        return fileRepository.findById(fileId).orElse(null);
    }

    public void updateFile(UUID fileId, File file) {
        Optional<File> fileOptional = fileRepository.findById(fileId);
        if (fileOptional.isPresent()) {
            File updatedFile = fileOptional.get();

            updatedFile.setFileName(file.getFileName());
            updatedFile.setDateCreated(file.getDateCreated());
            updatedFile.setDateModified(file.getDateModified());
            updatedFile.setIsShared(file.getIsShared());
            updatedFile.setFileBody(file.getFileBody());

            updatedFile.setUserString(file.getUserString());
            fileRepository.save(updatedFile);
        }
    }

    public void deleteFile(UUID fileId) {
        Optional<File> fileOptional = fileRepository.findById(fileId);
        if (fileOptional.isPresent()) {
            fileRepository.deleteById(fileId);
        }
    }

    // USERS
    // add a file to a user
    public void addFileToUser(UUID userId, UUID fileId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            return;
        File file = readFile(fileId);
        if (file == null)
            return;

        user.getFileIds().add(fileId); // serialize file id

        file.setUser(user);
        file.setUserString(userId.toString());
        userRepository.save(user);
        fileRepository.save(file);
    }

    // remove a file from a user
    public void removeFileFromUser(UUID userId, UUID fileId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            return;
        File file = readFile(fileId);
        if (file == null)
            return;
        user.getFileIds().remove(fileId);
        file.setUser(null);
        userRepository.save(user);
    }

    // DIRECTORY
    public void addFileToDirectory(UUID directoryId, UUID fileId) {
        Directory directory = directoryRepository.findById(directoryId).orElse(null);
        if (directory == null)
            return;
        File file = readFile(fileId);
        if (file == null)
            return;
        directory.getFiles().add(file);
        file.setFolder(directory);
        directoryRepository.save(directory);
    }

    public void removeFileFromDirectory(UUID directoryId, UUID fileId) {
        Directory directory = directoryRepository.findById(directoryId).orElse(null);
        if (directory == null)
            return;
        File file = readFile(fileId);
        if (file == null)
            return;
        directory.getFiles().remove(file);
        file.setFolder(null);
        directoryRepository.save(directory);
    }

    public void addFileToCourse(UUID courseId, UUID fileId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            return;
        File file = readFile(fileId);
        if (file == null)
            return;
        course.getFiles().add(file);
        file.setFolder(course);
        courseRepository.save(course);
    }

    public void removeFileFromCourse(UUID courseId, UUID fileId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            return;
        File file = readFile(fileId);
        if (file == null)
            return;
        course.getFiles().remove(file);
        file.setFolder(null);
        courseRepository.save(course);
    }
}
