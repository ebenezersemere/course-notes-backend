package coursenotes.backend.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // get
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserModel getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    // create
    @PostMapping("/user/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    // update
    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable int id, @RequestBody UserModel user) {
        userService.updateUser(id, user);
    }

    // delete
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }



}
