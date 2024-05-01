package coursenotes.backend.course;

import coursenotes.backend.user.User;
import coursenotes.backend.user.UserRepository;
import coursenotes.backend.user.UserService;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserService userService, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course readCourse(UUID courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public void updateCourse(UUID courseId, Course course) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course updatedCourse = courseOptional.get();
            updatedCourse.setFolderName(course.getFolderName());


            // Update files if needed
            if (course.getFiles() != null) {
                updatedCourse.getFiles().clear();
                updatedCourse.getFiles().addAll(course.getFiles());
            }

            // Update child folders if needed
            if (course.getChildFolders() != null) {
                updatedCourse.getChildFolders().clear();
                updatedCourse.getChildFolders().addAll(course.getChildFolders());
            }

            // Update users if needed
            if (course.getUsers() != null) {
                updatedCourse.getUsers().clear();
                updatedCourse.getUsers().addAll(course.getUsers());
            }

            updatedCourse.setParentFolder(course.getParentFolder());
            updatedCourse.setCourseUID(course.getCourseUID());
            updatedCourse.setCourseDescription(course.getCourseDescription());
            updatedCourse.setCourseLocation(course.getCourseLocation());
            updatedCourse.setCourseSemester(course.getCourseSemester());
            courseRepository.save(updatedCourse);
        }
    }

    public void deleteCourse(UUID courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            courseRepository.deleteById(courseId);
        }
    }

    public void addCourseToUser(UUID userId, UUID courseId) {
        User user = userService.readUser(userId);
        if (user == null)
            return;
        Course course = readCourse(courseId);
        if (course == null)
            return;
        user.getCourses().add(course);
        userRepository.save(user);
    }

    public void removeCourseFromUser(UUID userId, UUID courseId) {
        User user = userService.readUser(userId);
        if (user == null)
            return;
        Course course = readCourse(courseId);
        if (course == null)
            return;
        user.getCourses().remove(course);
        userRepository.save(user);
    }

//    public List<Course> getCoursesByUser(UUID userID) {
//        return courseRepository.findCoursesByUserId(userID);
//    }

//    /**
//     * uploadSchedule takes a scheduleLink and populates a list of CourseModel objects
//     * @param scheduleLink a String representing the URL of the .ics file
//     * @return <List<CourseModel>> a list of CourseModel objects retrieved from the .ics link
//     * @throws ParserException if there is an error parsing the .ics file using ical4j
//     * @throws IOException if there is an error reading the .ics file from the URL
//     */
//    public List<Course> uploadSchedule(String scheduleLink) throws ParserException, IOException {
//        List<Course> courses = extractInfoFromCalendar(scheduleLink);
//        return courseRepository.saveAll(courses);
//    }
//
//    /**
//     * extractInfoFromCalendar takes an .ics fileURL and returns a list of CourseModel objects
//     * @param scheduleLink the URL of the .ics file (String)
//     * @return a ResponseEntity containing the list of CourseModel objects (List<CourseModel>)
//     * @throws ParserException if there is an error parsing the .ics file using ical4j
//     * @throws IOException if there is an error reading the .ics file from the URL
//     */
//    private List<Course> extractInfoFromCalendar(String scheduleLink) throws ParserException, IOException {
//        // download file
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity(scheduleLink, String.class);
//        String coursesBody = response.getBody();
//        CalendarBuilder builder = new CalendarBuilder();
//        Calendar calendar = builder.build(new StringReader(coursesBody));
//
//        // populate list of CourseModel objects
//        List<Course> courses = new ArrayList<>();
//        for (CalendarComponent component : calendar.getComponents()) {
//            if (component instanceof VEvent event) {
//                // Extract event properties
//                Course eventCourse = new Course();
//
//                String uid = event.getProperty(Property.UID).isPresent() ? event.getProperty(Property.UID).get().getValue() : null;
//                String name = event.getProperty(Property.SUMMARY).isPresent() ? event.getProperty(Property.SUMMARY).get().getValue() : null;
//                String description = event.getProperty(Property.DESCRIPTION).isPresent() ? event.getProperty(Property.DESCRIPTION).get().getValue() : null;
//                String location = event.getProperty(Property.LOCATION).isPresent() ? event.getProperty(Property.LOCATION).get().getValue() : null;
//
//                // create Course object
//                Course course = new Course();
//                course.setFolderId(null);
//                course.setFolderName(name);
//                course.setFiles(null);
//                course.setChildFolders(null);
//                course.setParentFolder(null);
//
//                course.setCourseUID(uid);
//                course.setCourseDescription(description);
//                course.setCourseLocation(location);
//                course.setCourseSemester(Course.courseSemester.Spring2024);
//                course.setUsers(null); // TODO: add users to course
//
//                // add Course object to list
//                courses.add(course);
//            }
//        }
//        return courses;
//    }
}
