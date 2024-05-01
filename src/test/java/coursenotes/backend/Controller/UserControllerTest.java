package coursenotes.backend.Controller;

import coursenotes.backend.user.User;
import coursenotes.backend.user.UserRepository;
import coursenotes.backend.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;

@Nested
@SpringBootTest
@Configuration
@TestPropertySource(locations = "classpath:application.yml")

@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    private User mockUser;
    private UserService userService;


    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    // test that a user is being created

    @Test
    public void testCreateUser() throws Exception {
        mockUser = new User();
        UUID uuid = UUID.randomUUID();
        mockUser.setUserId(uuid);
        mockUser.setFirstName("Amy");
        mockUser.setLastName("Liu");
        mockUser.setEmail("amyliu@g.hmc.com");

        when(userService.createUser(any(User.class))).thenReturn(mockUser);
        String inputJson = new ObjectMapper().writeValueAsString(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(uuid))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Amy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Liu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("amyliu@g.hmc.com"))
        ;
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testReadUser() throws Exception {
        mockUser = new User();
        UUID uuid = UUID.randomUUID();
        mockUser.setUserId(uuid);
        mockUser.setFirstName("Amy");
        mockUser.setLastName("Liu");
        mockUser.setEmail("amyliu@g.hmc.com");

        when(userService.readUser(any(UUID.class))).thenReturn(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(uuid))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Amy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Liu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("amyliu@g.hmc.com"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockUser = new User();
        UUID uuid = UUID.randomUUID();
        mockUser.setUserId(uuid);
        mockUser.setFirstName("Amy");
        mockUser.setLastName("Liu");
        mockUser.setEmail("amyliu@g.hmc.com");

        userRepository.save(mockUser);

        userRepository.deleteById(mockUser.getUserId());
        Optional<User> deletedUser = userRepository.findById(mockUser.getUserId());

        Assertions.assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testUpdateUser() throws Exception {
        mockUser = new User();
        UUID uuid = UUID.randomUUID();
        mockUser.setUserId(uuid);
        mockUser.setFirstName("Amy");
        mockUser.setLastName("Liu");
        mockUser.setEmail("amyliu@g.hmc.com");

        User updatedUser = new User();
        UUID uuid2 = UUID.randomUUID();
        updatedUser.setUserId(uuid2);
        updatedUser.setFirstName("Xiyuan");
        updatedUser.setLastName("Liuu");
        updatedUser.setEmail("amyliu02@g.hmc.com");

        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
    }

    @Test
    public void testGetUsers() throws Exception {
        User mockUser = new User();
        UUID uuid = UUID.randomUUID();
        mockUser.setUserId(uuid);
        mockUser.setFirstName("Amy");
        mockUser.setLastName("Liu");
        mockUser.setEmail("amyliu@g.hmc.com");

        User user2 = new User();
        UUID uuid2 = UUID.randomUUID();
        user2.setUserId(uuid2);
        user2.setFirstName("Ebenezer");
        user2.setLastName("Semere");
        user2.setEmail("semere.ebenezer@g.pomona.com");

        List<User> userList = Arrays.asList(mockUser, user2);

        when(userService.getUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(uuid))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Amy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Liu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("amyliu@g.hmc.com"));
    }
}






