package coursenotes.backend.course;

import net.fortuna.ical4j.data.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/uploadSchedule")
    public ResponseEntity<List<CourseModel>> uploadSchedule(@RequestParam String scheduleLink) throws ParserException, IOException {
        return courseService.uploadSchedule(scheduleLink);
    }
    @GetMapping("/{courseId}/folders")
    public List<Folder> getFoldersInCourse(@PathVariable Long courseId) {
        return courseService.getFoldersInCourse(courseId);
    }

}
