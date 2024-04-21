package coursenotes.backend.directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DirectoryController {
    private final DirectoryService directoryService;

    @Autowired
    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    // create a directory
    @PostMapping("/directories")
    public ResponseEntity<Directory> createDirectory(@RequestBody Directory directory) {
        Directory createdDirectory = directoryService.createDirectory(directory);
        return ResponseEntity.ok(createdDirectory);
    }

    // read a directory by its id
    @GetMapping("/directories/{directoryId}")
    public ResponseEntity<Directory> readDirectory(@PathVariable UUID directoryId) {
        Directory directory = directoryService.readDirectory(directoryId);
        return ResponseEntity.ok(directory);
    }

    // update a directory by its id
    @PutMapping("/directories/{directoryId}")
    public void updateDirectory(@PathVariable UUID directoryId, @RequestBody Directory directory) {
        directoryService.updateDirectory(directoryId, directory);
    }

    // delete a directory
    @DeleteMapping("/directories/{directoryId}")
    public void deleteDirectory(@PathVariable UUID directoryId) {
        directoryService.deleteDirectory(directoryId);
    }

    // USERS
    // add a directory to a user
    @PutMapping("/directories/{directoryId}/users/{userId}")
    public void addDirectoryToUser(@PathVariable UUID userId, @PathVariable UUID directoryId) {
        directoryService.addDirectoryToUser(userId, directoryId);
    }

    // remove a directory from a user
    @DeleteMapping("/directories/{directoryId}/users/{userId}")
    public void removeDirectoryFromUser(@PathVariable UUID userId, @PathVariable UUID directoryId) {
        directoryService.removeDirectoryFromUser(userId, directoryId);
    }


}
