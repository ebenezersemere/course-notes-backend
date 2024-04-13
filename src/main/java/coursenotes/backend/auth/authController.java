package coursenotes.backend.auth;

import coursenotes.backend.user.UserModel;
import coursenotes.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class authController {
    private UserService userService;

    @Autowired
    public void UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public UserModel registerUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @PostMapping("/auth/signin")
    public void loginUser(@RequestBody UserModel user) {
        // TODO oauth logic
    }

    @@PostMapping("/auth/signout")
    public void logoutUser(@RequestBody UserModel user) {
        // TODO oauth logic
    }

}
