package coursenotes.backend.directory;

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

    @Autowired
    public DirectoryService(DirectoryRepository directoryRepository, UserService userService, UserRepository userRepository, FileService fileService) {
        this.directoryRepository = directoryRepository;
        this.userService = userService;
        this.userRepository = userRepository;
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
        userRepository.save(user);
    }


}
