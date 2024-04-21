package coursenotes.backend.user;

import coursenotes.backend.file.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    // create a user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // read a user
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> readUser(@PathVariable UUID userId) {
        User user = userService.readUser(userId);
        return ResponseEntity.ok(user);
    }

    // update a user
    @PutMapping("/users/{userId}")
    public void updateUser(@PathVariable UUID userId, @RequestBody User user) {
        userService.updateUser(userId, user);
    }

    // delete a user
    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }

    // add a file to a user
    @PutMapping("/users/{userId}/files/{fileId}")
    public void addFile(@PathVariable UUID userId, @PathVariable UUID fileId) {
        userService.addFile(userId, fileId);
    }

    // remove a file from a user
    @DeleteMapping("/users/{userId}/files/{fileId}")
    public void removeFile(@PathVariable UUID userId, @PathVariable UUID fileId) {
        userService.removeFile(userId, fileId);
    }

    // add a directory to a user
    @PutMapping("/users/{userId}/directories/{directoryId}")
    public void addDirectory(@PathVariable UUID userId, @PathVariable UUID directoryId) {
        userService.addDirectory(userId, directoryId);
    }

    // remove a directory from a user
    @DeleteMapping("/users/{userId}/directories/{directoryId}")
    public void removeDirectory(@PathVariable UUID userId, @PathVariable UUID directoryId) {
        userService.removeDirectory(userId, directoryId);
    }

    // add a course to a user
    @PutMapping("/users/{userId}/courses/{courseId}")
    public void addCourse(@PathVariable UUID userId, @PathVariable UUID courseId) {
        userService.addCourse(userId, courseId);
    }

    // remove a course from a user
    @DeleteMapping("/users/{userId}/courses/{courseId}")
    public void removeCourse(@PathVariable UUID userId, @PathVariable UUID courseId) {
        userService.removeCourse(userId, courseId);
    }


//    // get
//    @GetMapping("/user")
//    @ResponseStatus(HttpStatus.OK)
//    public UserModel getUser(@AuthenticationPrincipal OAuth2User principal) {
//        return userService.getUser(principal);
//    }
}
