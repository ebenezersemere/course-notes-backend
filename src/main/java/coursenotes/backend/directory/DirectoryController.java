package coursenotes.backend.directory;

import coursenotes.backend.user.UserModel;
import coursenotes.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

public class DirectoryController {
    @Autowired
    private DirectoryService directoryService;

    // get
    @GetMapping("/directory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserModel getDirectory(@PathVariable Long id) {
        return directoryService.getDirectory(id);
    }

    // create
    @PostMapping("/directory/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createDirectory(@RequestBody UserModel user) {
        return directoryService.createDirectory(directory);
    }

    // update
    @PutMapping("/directory/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDirectory(@PathVariable Long id, @RequestBody UserModel user) {directoryService.updateDirectory(id, directory);
    }

    // delete
    @DeleteMapping("/directory/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirectory(@PathVariable Long id) {
        directoryService.deleteDirectory(id);
    }
}

}
