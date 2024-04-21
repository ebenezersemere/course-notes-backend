package coursenotes.backend.folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FolderController<T extends Folder> {

    private final FolderService<T> folderService;

    @Autowired
    public FolderController(FolderService<T> folderService) {
        this.folderService = folderService;
    }

    // Implement your controller methods here using folderService
}
//
//@Repository
//public interface DirectoryRepository extends FolderRepository<Directory> {
//}
//
//@Service
//public class DirectoryService extends FolderService<Directory> {
//    @Autowired
//    public DirectoryService(DirectoryRepository directoryRepository) {
//        super(directoryRepository);
//    }
//}
//
//@RestController
//@RequestMapping("/directories")
//public class DirectoryController extends FolderController<Directory> {
//    @Autowired
//    public DirectoryController(DirectoryService directoryService) {
//        super(directoryService);
//    }
//}
//
//@Repository
//public interface CourseRepository extends FolderRepository<Course> {
//}
//
//@Service
//public class CourseService extends FolderService<Course> {
//    @Autowired
//    public CourseService(CourseRepository courseRepository) {
//        super(courseRepository);
//    }
//}
//
//@RestController
//@RequestMapping("/courses")
//public class CourseController extends FolderController<Course> {
//    @Autowired
//    public CourseController(CourseService courseService) {
//        super(courseService);
//    }
//}