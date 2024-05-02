package coursenotes.backend.User;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import coursenotes.backend.user.User;
import coursenotes.backend.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
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


    @Test
    public void testCreateUser() throws Exception {
        mockUser = new User();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockUser.setUserId(uuid);
        mockUser.setFirstName("first");
        mockUser.setLastName("last");
        mockUser.setEmail("email.com");

        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        String inputJson = new ObjectMapper().writeValueAsString(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(uuid.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("last"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email.com"));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testReadUser() throws Exception {
        // Create a mock user object
        User mockUser = new User();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockUser.setUserId(uuid);
        mockUser.setFirstName("Amy");
        mockUser.setLastName("Liu");
        mockUser.setEmail("amyliu@g.hmc.com");

        // Configure the mock repository to return the mock user when findById is called with the specific UUID
        when(userRepository.findById(uuid)).thenReturn(Optional.of(mockUser));

        // Perform the GET request to /users/{uuid} endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(uuid.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Amy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Liu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("amyliu@g.hmc.com"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        mockUser = new User();
        UUID uuid = UUID.randomUUID();
        mockUser.setUserId(uuid);
        mockUser.setFirstName("Amy");
        mockUser.setLastName("Liu");
        mockUser.setEmail("amyliu@g.hmc.com");


//        mockUser.setUserId(uuid2);
        User updatedUser = new User();
        updatedUser.setFirstName("Xiyuan");
        updatedUser.setLastName("Liuu");
        updatedUser.setEmail("amyliu02@g.hmc.com");

        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Call the method under test to update the user
        User result = userRepository.save(mockUser);

        // Verify that the user was updated correctly
        assertEquals(updatedUser.getUserId(), result.getUserId());
        assertEquals(updatedUser.getFirstName(), result.getFirstName());
        assertEquals(updatedUser.getLastName(), result.getLastName());
        assertEquals(updatedUser.getEmail(), result.getEmail());
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Create a mock user object
        User mockUser = new User();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockUser.setUserId(uuid);
        mockUser.setFirstName("Amy");
        mockUser.setLastName("Liu");
        mockUser.setEmail("amyliu@g.hmc.com");

        // Configure the mock repository to return true when existsById is called with the specific UUID
        when(userRepository.existsById(uuid)).thenReturn(true);

        // Perform the DELETE request to /users/{uuid} endpoint
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetUsers() throws Exception {
        // Create mock user objects
        User mockUser1 = new User();
        UUID uuid1 = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockUser1.setUserId(uuid1);
        mockUser1.setFirstName("Amy");
        mockUser1.setLastName("Liu");
        mockUser1.setEmail("amyliu@g.hmc.com");

        User mockUser2 = new User();
        UUID uuid2 = UUID.randomUUID();
        mockUser2.setUserId(uuid2);
        mockUser2.setFirstName("Ebenezer");
        mockUser2.setLastName("Semere");
        mockUser2.setEmail("semere.ebenezer@g.pomona.com");

        List<User> userList = Arrays.asList(mockUser1, mockUser2);

        // Configure the mock service to return the list of users when getUsers is called

        when(userRepository.findAll()).thenReturn(userList);

        // Perform the GET request to /users endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(uuid1.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Amy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Liu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("amyliu@g.hmc.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userId").value(uuid2.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Ebenezer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Semere"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("semere.ebenezer@g.pomona.com"));
    }

}
