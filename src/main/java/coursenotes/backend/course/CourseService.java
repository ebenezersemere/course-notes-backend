package coursenotes.backend.course;

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
    public CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
            updatedCourse.setFiles(course.getFiles());
            updatedCourse.setChildFolders(course.getChildFolders());
            updatedCourse.setParentFolder(course.getParentFolder());
            updatedCourse.setCourseUID(course.getCourseUID());
            updatedCourse.setCourseDescription(course.getCourseDescription());
            updatedCourse.setCourseLocation(course.getCourseLocation());
            updatedCourse.setCourseSemester(course.getCourseSemester());
            updatedCourse.setUsers(course.getUsers());
            courseRepository.save(updatedCourse);
        }
    }

    public void deleteCourse(UUID courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            courseRepository.deleteById(courseId);
        }
    }

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
