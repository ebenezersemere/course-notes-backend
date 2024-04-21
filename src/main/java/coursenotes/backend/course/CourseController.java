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
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.ok(createdCourse);
    }

    // read a course by its id
    @GetMapping("/courses/{courseID}")
    public ResponseEntity<Course> readCourse(@PathVariable UUID courseID) {
        Course course = courseService.readCourse(courseID);
        return ResponseEntity.ok(course);
    }

    // update a course by its id
    @PutMapping("/courses/{courseID}")
    public void updateCourse(@PathVariable UUID courseID, @PathVariable Course course) {
        courseService.updateCourse(courseID, course);
    }

    // delete a course
    @DeleteMapping("/courses/{courseID}")
    public void deleteCourse(@PathVariable UUID courseID) {
        courseService.deleteCourse(courseID);
    }

    // USERS
//    @GetMapping("/courses/users/{userID}")
//    public ResponseEntity<List<Course>> getCoursesByUser(@PathVariable UUID userID) {
//        List<Course> courses = courseService.getCoursesByUser(userID);
//        return ResponseEntity.ok(courses);
//    }

    // add a course to a user
    @PutMapping("/courses/{courseID}/users/{userID}")
    public void addCourseToUser(@PathVariable UUID userID, @PathVariable UUID courseID) {
        courseService.addCourseToUser(userID, courseID);
    }

    // remove a course from a user
    @DeleteMapping("/courses/{courseID}/users/{userID}")
    public void removeCourseFromUser(@PathVariable UUID userID, @PathVariable UUID courseID) {
        courseService.removeCourseFromUser(userID, courseID);
    }


//    @PostMapping("/uploadSchedule")
//    public ResponseEntity<List<Course>> uploadSchedule(@RequestParam String scheduleLink) throws ParserException, IOException {
//        List<Course> courses = courseService.uploadSchedule(scheduleLink);
//        return ResponseEntity.ok(courses);
//    }

}
