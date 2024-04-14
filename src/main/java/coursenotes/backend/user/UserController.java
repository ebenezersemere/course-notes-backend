package coursenotes.backend.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // get
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UserModel getUser(@AuthenticationPrincipal OAuth2User principal) {
        return userService.getUser(principal);
    }

    // update
    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        userService.updateUser(id, user);
    }

    // delete
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
