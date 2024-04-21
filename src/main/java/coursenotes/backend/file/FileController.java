package coursenotes.backend.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // create a file
    @PostMapping("/files")
    public ResponseEntity<File> createFile(@RequestBody File file) {
        File createdFile = fileService.createFile(file);
        return ResponseEntity.ok(createdFile);
    }

    // read a file by its id
    @GetMapping("/files/{fileId}")
    public ResponseEntity<File> readFile(@PathVariable UUID fileId) {
        File file = fileService.readFile(fileId);
        return ResponseEntity.ok(file);
    }

    // update a file by its id
    @PutMapping("/files/{fileId}")
    public void updateFile(@PathVariable UUID fileId, @RequestBody File file) {
        fileService.updateFile(fileId, file);
    }

    // delete a file
    @DeleteMapping("/files/{fileId}")
    public void deleteFile(@PathVariable UUID fileId) {
        fileService.deleteFile(fileId);
    }

    // USERS
    // add a file to a user
    @PutMapping("/files/{fileId}/users/{userId}/")
    public void addFileToUser(@PathVariable UUID userId, @PathVariable UUID fileId) {
        fileService.addFileToUser(userId, fileId);
    }

    // remove a file from a user
    @DeleteMapping("/files/{fileId}/users/{userId}/")
    public void removeFileFromUser(@PathVariable UUID userId, @PathVariable UUID fileId) {
        fileService.removeFileFromUser(userId, fileId);
    }

    // DIRECTORY
    // add a file to a directory
    @PutMapping("/files/{fileId}/directories/{directoryId}")
    public void addFileToDirectory(@PathVariable UUID directoryId, @PathVariable UUID fileId) {
        fileService.addFileToDirectory(directoryId, fileId);
    }

    // remove a file from a directory
    @DeleteMapping("/files/{fileId}/directories/{directoryId}")
    public void removeFileFromDirectory(@PathVariable UUID directoryId, @PathVariable UUID fileId) {
        fileService.removeFileFromDirectory(directoryId, fileId);
    }
}
