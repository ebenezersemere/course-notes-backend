//package coursenotes.backend.course;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@WebMvcTest(CourseController.class)
//@AutoConfigureMockMvc
//public class CourseControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AuthenticationManager authenticationManager;
//
//    @MockBean
//    private CourseService courseService;
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void testUploadSchedule() throws Exception {
//
//    // arrange
//    CourseModel course1 = new CourseModel(); // Set properties as needed
//    CourseModel course2 = new CourseModel(); // Set properties as needed
//    List<CourseModel> expectedCourses = Arrays.asList(course1, course2);
//    String scheduleLink = "mockScheduleLink";
//
//    when(courseService.uploadSchedule(scheduleLink)).thenReturn(ResponseEntity.ok(expectedCourses));
//
//    // act
//    MockHttpServletResponse response = mockMvc.perform(
//            post("/uploadSchedule")
//                    .param("scheduleLink", scheduleLink)
//                    .accept(APPLICATION_JSON)
//    ).andReturn().getResponse();
//
//    // assert
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
//    ObjectMapper mapper = new ObjectMapper();
//    String expectedJson = mapper.writeValueAsString(expectedCourses);
//    assertThat(response.getContentAsString()).isEqualTo(expectedJson);
//
//    }
//}
