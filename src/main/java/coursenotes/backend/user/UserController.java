package coursenotes.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    // get
//    @GetMapping("/user")
//    @ResponseStatus(HttpStatus.OK)
//    public UserModel getUser(@AuthenticationPrincipal OAuth2User principal) {
//        return userService.getUser(principal);
//    }

    // update
    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable UUID userId, @RequestBody User user) {
        userService.updateUser(userId, user);
    }

    // delete
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }
}
