package coursenotes.backend.directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DirectoryService {
    private final DirectoryRepository directoryRepository;

    @Autowired
    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
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
}
