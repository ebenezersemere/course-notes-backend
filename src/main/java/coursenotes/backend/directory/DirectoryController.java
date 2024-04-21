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

    // DIRECTORY
    // add a directory to a directory
    @PutMapping("/directories/{directoryId}/directories/{parentDirectoryId}")
    public void addDirectoryToDirectory(@PathVariable UUID directoryId, @PathVariable UUID parentDirectoryId) {
        directoryService.addDirectoryToDirectory(directoryId, parentDirectoryId);
    }

    // remove a directory from a directory
    @DeleteMapping("/directories/{directoryId}/directories/{parentDirectoryId}")
    public void removeDirectoryFromDirectory(@PathVariable UUID directoryId, @PathVariable UUID parentDirectoryId) {
        directoryService.removeDirectoryFromDirectory(directoryId, parentDirectoryId);
    }

    // COURSES
    // add a directory to a course
    @PutMapping("/directories/{directoryId}/courses/{parentCourseId}")
    public void addDirectoryToCourse(@PathVariable UUID directoryId, @PathVariable UUID parentCourseId) {
        directoryService.addDirectoryToCourse(directoryId, parentCourseId);
    }

    // remove a directory from a course
    @DeleteMapping("/directories/{directoryId}/courses/{parentCourseId}")
    public void removeDirectoryFromCourse(@PathVariable UUID directoryId, @PathVariable UUID parentCourseId) {
        directoryService.removeDirectoryFromCourse(directoryId, parentCourseId);
    }

}
