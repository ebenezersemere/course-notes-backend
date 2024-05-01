package coursenotes.backend.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import coursenotes.backend.course.CourseService;
import coursenotes.backend.course.CourseRepository;
import coursenotes.backend.course.Course;
import coursenotes.backend.directory.DirectoryService;
import coursenotes.backend.directory.DirectoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@WebMvcTest(Course.class)
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private CourseService courseService;
    private CourseRepository courseRepository;
    private ObjectMapper mapper;

    @Test
    public void shouldCreateCourse() throws Exception {
        Course course = new Course();
        // Set your course fields here
        when(courseService.createCourse(any(Course.class))).thenReturn(course);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(course)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReadCourse() throws Exception {
        UUID courseId = UUID.randomUUID();
        Course course = new Course();
        // Set course fields here
        when(courseService.readCourse(any(UUID.class))).thenReturn(course);

        mockMvc.perform(get("/courses/{id}", courseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateCourse() throws Exception {
        UUID courseId = UUID.randomUUID();
        Course course = new Course();// Set course fields here

        mockMvc.perform(put("/courses/{id}", courseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(course)))
                .andExpect(status().isOk());

        verify(courseService, times(1)).updateCourse(courseId, course);
    }

    @Test
    public void shouldDeleteCourse() throws Exception {
        UUID courseId = UUID.randomUUID();

        mockMvc.perform(delete("/courses/{id}", courseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(courseService, times(1)).deleteCourse(courseId);
    }
}

