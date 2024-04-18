package coursenotes.backend.user;

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

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // read a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> readUser(@PathVariable UUID userId) {
        User user = userService.readUser(userId);
        return ResponseEntity.ok(user);
    }

    // update
    @PutMapping("/user/{userId}")
    public void updateUser(@PathVariable UUID userId, @RequestBody User user) {
        userService.updateUser(userId, user);
    }

    // delete
    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }


//    // get
//    @GetMapping("/user")
//    @ResponseStatus(HttpStatus.OK)
//    public UserModel getUser(@AuthenticationPrincipal OAuth2User principal) {
//        return userService.getUser(principal);
//    }
}
