package coursenotes.backend.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import coursenotes.backend.user.User;
import coursenotes.backend.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test // Create
    public void testAddAuthor() throws Exception {
        mockUser = new User();
        mockUser.setFirstName("Amy");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        String inputJson = new ObjectMapper().writeValueAsString(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Amy"));

        verify(userRepository, times(1)).save(any(User.class));

    }

}
