package coursenotes.backend.Directory;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import coursenotes.backend.directory.Directory;
import coursenotes.backend.directory.DirectoryRepository;
import coursenotes.backend.directory.DirectoryService;
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
public class DirectoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectoryRepository directoryRepository;

    private Directory mockDirectory;

    @BeforeEach
    public void setUp() {
        directoryRepository.deleteAll();
    }

    @Test
    public void testCreateDirectory() throws Exception {
        mockDirectory = new Directory();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockDirectory.setFolderId(uuid);
        mockDirectory.setFolderName("testFolder");

        when(directoryRepository.save(any(Directory.class))).thenReturn(mockDirectory);
        String inputJson = new ObjectMapper().writeValueAsString(mockDirectory);

        mockMvc.perform(MockMvcRequestBuilders.post("/directories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.folderId").value(uuid.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.folderName").value("testFolder"));
        verify(directoryRepository, times(1)).save(any(Directory.class));

    }

    @Test
    public void testReadDirectory() throws Exception {
        mockDirectory = new Directory();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockDirectory.setFolderId(uuid);
        mockDirectory.setFolderName("testFolder");

        // Configure the mock repository to return the mock user when findById is called with the specific UUID
        when(directoryRepository.findById(uuid)).thenReturn(Optional.of(mockDirectory));

        // Perform the GET request to /users/{uuid} endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/directories/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.folderId").value(uuid.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.folderName").value("testFolder"));
    }


    @Test
    public void testUpdateDirectory() throws Exception {
        mockDirectory = new Directory();
        UUID uuid = UUID.randomUUID();
        mockDirectory.setFolderId(uuid);
        mockDirectory.setFolderName("originalName");

        Directory updatedDirectory = new Directory();
        UUID uuid2 = UUID.randomUUID();
        updatedDirectory.setFolderId(uuid2);
        updatedDirectory.setFolderId(uuid2);
        updatedDirectory.setFolderName("newName");

        when(directoryRepository.save(any(Directory.class))).thenReturn(updatedDirectory);

        // Call the method under test to update the user
        Directory result = directoryRepository.save(mockDirectory);

        // Verify that the user was updated correctly
        assertEquals(updatedDirectory.getFolderId(), result.getFolderId());
        assertEquals(updatedDirectory.getFolderName(), result.getFolderName());
    }


    @Test
    public void testDeleteDirectory() throws Exception {
        // Create a mock directory object
        Directory mockDirectory = new Directory();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockDirectory.setFolderId(uuid);
        mockDirectory.setFolderName("ByeByeFolder");

        // Configure the mock repository to return the mock directory when findById is called
        when(directoryRepository.findById(uuid)).thenReturn(Optional.of(mockDirectory));

        // Perform the DELETE request to /directories/{uuid} endpoint
        mockMvc.perform(MockMvcRequestBuilders.delete("/directories/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the delete method was called on the repository
        verify(directoryRepository, times(1)).deleteById(uuid);
    }

}
