package coursenotes.backend.file;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
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
            updatedFile.setAuthor(file.getAuthor());
            fileRepository.save(updatedFile);
        }
    }

    public void deleteFile(UUID fileId) {
        Optional<File> fileOptional = fileRepository.findById(fileId);
        if (fileOptional.isPresent()) {
            fileRepository.deleteById(fileId);
        }
    }
}
