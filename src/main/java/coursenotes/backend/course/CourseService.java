package coursenotes.backend.course;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    /**
     * uploadSchedule takes a scheduleLink and populates a list of CourseModel objects
     * @param scheduleLink a String representing the URL of the .ics file
     * @return <List<CourseModel>> a list of CourseModel objects retrieved from the .ics link
     * @throws ParserException if there is an error parsing the .ics file using ical4j
     * @throws IOException if there is an error reading the .ics file from the URL
     */
    public ResponseEntity<List<Course>> uploadSchedule(String scheduleLink) throws ParserException, IOException {
        List<Course> extractedInfo = extractInfoFromCalendar(scheduleLink);
        return ResponseEntity.ok(extractedInfo);
    }

    /**
     * extractInfoFromCalendar takes an .ics fileURL and returns a list of CourseModel objects
     * @param scheduleLink the URL of the .ics file (String)
     * @return a ResponseEntity containing the list of CourseModel objects (List<CourseModel>)
     * @throws ParserException if there is an error parsing the .ics file using ical4j
     * @throws IOException if there is an error reading the .ics file from the URL
     */
    private List<Course> extractInfoFromCalendar(String scheduleLink) throws ParserException, IOException {
        // download file
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(scheduleLink, String.class);
        String coursesBody = response.getBody();
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(new StringReader(coursesBody));

        // populate list of CourseModel objects
        List<Course> courses = new ArrayList<>();
        for (CalendarComponent component : calendar.getComponents()) {
            if (component instanceof VEvent event) {
                // Extract event properties
                Course eventCourse = new Course();

                String uid = event.getProperty(Property.UID).isPresent() ? event.getProperty(Property.UID).get().getValue() : null;
                String name = event.getProperty(Property.SUMMARY).isPresent() ? event.getProperty(Property.SUMMARY).get().getValue() : null;
                String description = event.getProperty(Property.DESCRIPTION).isPresent() ? event.getProperty(Property.DESCRIPTION).get().getValue() : null;
                String location = event.getProperty(Property.LOCATION).isPresent() ? event.getProperty(Property.LOCATION).get().getValue() : null;

                // create CourseModel object
                Course course = new Course(null, uid, name, description, location, null);
                courses.add(course);
            }
        }
        return courses;
    }
}
