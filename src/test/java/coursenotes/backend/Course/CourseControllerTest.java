package coursenotes.backend.Course;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import coursenotes.backend.course.Course;
import coursenotes.backend.course.CourseRepository;
import coursenotes.backend.course.CourseService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CourseRepository courseRepository;
    private ObjectMapper mapper;
    private Course mockCourse;
    private CourseService courseService;
    @BeforeEach
    public void setUp() {courseRepository.deleteAll();}

    @Test
    public void testCreateCourse() throws Exception {
        Course course = new Course();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        course.setCourseUID("CSCI181AA");
        course.setCourseLocation("McGregor");
        course.setCourseDescription("Software Engineering");// Set your course fields here
        course.setFolderId(uuid);
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        String inputJson = new ObjectMapper().writeValueAsString(course);

        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseDescription").value("Software Engineering"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseLocation").value("McGregor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseUID").value("CSCI181AA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.folderId").value(uuid.toString()));
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    public void testReadCourse() throws Exception {
        Course course = new Course();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        course.setCourseUID("CSCI181AA");
        course.setCourseLocation("McGregor");
        course.setCourseDescription("Software Engineering");// Set your course fields here
        course.setFolderId(uuid);

        when(courseRepository.findById(uuid)).thenReturn(Optional.of(course));

        mockMvc.perform(MockMvcRequestBuilders.get("/courses/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseDescription").value("Software Engineering"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseLocation").value("McGregor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseUID").value("CSCI181AA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.folderId").value(uuid.toString()));
        //verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        Course course = new Course();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        course.setCourseUID("CSCI181AA");
        course.setCourseLocation("McGregor");
        course.setCourseDescription("Software Engineering");// Set your course fields here
        course.setFolderId(uuid);

        when(courseRepository.existsById(uuid)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

       // verify(courseRepository, times(1)).deleteById(uuid);
    }
//
//    @Test
//    public void shouldUpdateCourse() throws Exception {
//        UUID courseId = UUID.randomUUID();
//        Course course = new Course();
//        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
//        course.setCourseUID("CSCI181AA");
//        course.setCourseLocation("McGregor");
//        course.setCourseDescription("Software Engineering");
//        course.setFolderId(uuid);
//
//        Course updatedCourse = new Course();
//        updatedCourse.setCourseUID("CSCI105");
//        updatedCourse.setCourseLocation("Shanahan");
//        updatedCourse.setCourseDescription("Computer Systems");
//        updatedCourse.setFolderId(uuid);
//
//        when(courseRepository.save(course)).thenReturn(updatedCourse);
//
//        Course result = courseService.updateCourse(course);
//
//        assertEquals(updatedCourse.getCourseDescription(), result.getCourseDescription());
//        assertEquals(updatedCourse.getCourseLocation(), result.getCourseLocation());
//        assertEquals(updatedCourse.getCourseUID(), result.getCourseUID());
//        assertEquals(updatedCourse.getFolderId(), result.getFolderId());
//    }

}
//    @Test
//    public void shouldReadCourse() throws Exception {
//        UUID courseId = UUID.randomUUID();
//        Course course = new Course();
//        // Set course fields here
//        when(courseService.readCourse(any(UUID.class))).thenReturn(course);
//
//        mockMvc.perform(get("/courses/{id}", courseId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void shouldUpdateCourse() throws Exception {
//        UUID courseId = UUID.randomUUID();
//        Course course = new Course();// Set course fields here
//
//        mockMvc.perform(put("/courses/{id}", courseId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(course)))
//                .andExpect(status().isOk());
//
//        verify(courseService, times(1)).updateCourse(courseId, course);
//    }
//
//    @Test
//    public void shouldDeleteCourse() throws Exception {
//        UUID courseId = UUID.randomUUID();
//
//        mockMvc.perform(delete("/courses/{id}", courseId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(courseService, times(1)).deleteCourse(courseId);
//    }
//}



