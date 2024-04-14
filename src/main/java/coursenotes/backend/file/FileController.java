package coursenotes.backend.file;

import coursenotes.backend.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping
    public File uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.saveFile(file);
    }

    @GetMapping
    public List<File> getAllFiles() {
        return fileService.getAllFiles();
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        File file = fileService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }
//Maybe create a
    @PostMapping("/folders")
    public Folder createFolder(@RequestBody Folder newFolder) {
        return folderService.createFolder(newFolder);
    }

    @PutMapping("/files/{fileId}/move/{folderId}")
    public File moveFileToFolder(@PathVariable Long fileId, @PathVariable Long folderId) {
        return fileService.moveFileToFolder(fileId, folderId);
    }

    @GetMapping("/folders/{folderId}/files")
    public List<File> getAllFilesInFolder(@PathVariable Long folderId) {
        return fileService.getAllFilesInFolder(folderId);
    }

    @DeleteMapping("/folders/{folderId}")
    public ResponseEntity<?> deleteFolder(@PathVariable Long folderId) {
        folderService.deleteFolder(folderId);
        return ResponseEntity.ok().build();
    }
}
