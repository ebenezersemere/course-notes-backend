package coursenotes.backend.course;
import coursenotes.backend.course.Course;
import coursenotes.backend.course.CourseService;
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
    public ResponseEntity<List<Course>> uploadSchedule(@RequestParam String scheduleLink) throws ParserException, IOException {
        List<Course> courses = courseService.uploadSchedule(scheduleLink);
        return ResponseEntity.ok(courses);
    }

}
