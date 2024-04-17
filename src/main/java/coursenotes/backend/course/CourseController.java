package coursenotes.backend.course;
import coursenotes.backend.course.Course;
import coursenotes.backend.course.CourseService;
import net.fortuna.ical4j.data.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // create a course
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestParam Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.ok(createdCourse);
    }

    // read a course by its id
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> readCourse(@RequestParam UUID courseId) {
        Course course = courseService.readCourse(courseId);
        return ResponseEntity.ok(course);
    }

    // update a course by its id
    @PutMapping("/courses/{courseId}")
    public void updateCourse(@RequestParam UUID courseId, @RequestParam Course course) {
        courseService.updateCourse(courseId, course);
    }

    // delete a course
    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@RequestParam UUID courseId) {
        courseService.deleteCourse(courseId);
    }

//    @PostMapping("/uploadSchedule")
//    public ResponseEntity<List<Course>> uploadSchedule(@RequestParam String scheduleLink) throws ParserException, IOException {
//        List<Course> courses = courseService.uploadSchedule(scheduleLink);
//        return ResponseEntity.ok(courses);
//    }

}
