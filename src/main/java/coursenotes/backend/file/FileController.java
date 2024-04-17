package coursenotes.backend.file;

import coursenotes.backend.folder.Folder;
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
        return ResponseEntity.ok(file);
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
}
