package coursenotes.backend.file;

import coursenotes.backend.directory.Directory;
import coursenotes.backend.directory.DirectoryRepository;
import coursenotes.backend.directory.DirectoryService;
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

    public FileService(FileRepository fileRepository, UserRepository userRepository, DirectoryRepository directoryRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.directoryRepository = directoryRepository;
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
            File updatedFile = fileRepository.findById(fileId).get();
            updatedFile.setFileName(file.getFileName());

            updatedFile.setDateCreated(file.getDateCreated());
            updatedFile.setDateModified(file.getDateModified());
            updatedFile.setIsShared(file.getIsShared());
            updatedFile.setFileBody(file.getFileBody());
//            updatedFile.setUsers(file.getUsers());
            updatedFile.setFolder(file.getFolder());
            updatedFile.setUser(file.getUser());
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
        user.getFiles().add(file);
        userRepository.save(user);
    }

    // remove a file from a user
    public void removeFileFromUser(UUID userId, UUID fileId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null)
            return;
        File file = readFile(fileId);
        if (file == null)
            return;
        user.getFiles().remove(file);
        userRepository.save(user);
    }

    // DIRECTORY
    // add a file to a directory
    public void addFileToDirectory(UUID directoryId, UUID fileId) {
        Directory directory = directoryRepository.findById(directoryId).orElse(null);
        if (directory == null)
            return;
        File file = readFile(fileId);
        if (file == null)
            return;
        directory.getFiles().add(file);
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
        directoryRepository.save(directory);
    }
}
