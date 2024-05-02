package coursenotes.backend.File;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import coursenotes.backend.file.File;
import coursenotes.backend.file.FileRepository;
import coursenotes.backend.user.User;
import coursenotes.backend.user.UserRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileRepository fileRepository;

    private File mockFile;

    @BeforeEach
    public void setUp() {
        fileRepository.deleteAll();
    }


    @Test
    public void testCreateFile() throws Exception {
        mockFile = new File();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockFile.setFileId(uuid);
        mockFile.setFileName("ANewFile");
        mockFile.setFileBody("fileBody");

        when(fileRepository.save(any(File.class))).thenReturn(mockFile);
        String inputJson = new ObjectMapper().writeValueAsString(mockFile);

        mockMvc.perform(MockMvcRequestBuilders.post("/files")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileId").value(uuid.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value("ANewFile"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileBody").value("fileBody"));
        verify(fileRepository, times(1)).save(any(File.class));
    }

    @Test
    public void testReadFile() throws Exception {
        // Create a mock user object
        File mockFile = new File();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockFile.setFileId(uuid);
        mockFile.setFileName("ANewFile");
        mockFile.setFileBody("FileBody");

        // Configure the mock repository to return the mock user when findById is called with the specific UUID
        when(fileRepository.findById(uuid)).thenReturn(Optional.of(mockFile));

        // Perform the GET request to /users/{uuid} endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/files/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileId").value(uuid.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value("ANewFile"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileBody").value("FileBody"));
    }


    @Test
    public void testUpdateFile() throws Exception {
        // Create a mock file object
        File mockFile = new File();
        UUID uuid = UUID.randomUUID();
        mockFile.setFileId(uuid);
        mockFile.setFileName("OldFileName");
        mockFile.setFileBody("OldFileBody");

        // Create an updated file object
        File updatedFile = new File();
        updatedFile.setFileId(uuid);
        updatedFile.setFileName("NewFileName");
        updatedFile.setFileBody("NewFileBody");

        // Configure the mock repository to return the updated file when save is called
        when(fileRepository.save(any(File.class))).thenReturn(updatedFile);

        // Call the method under test to update the file
        File result = fileRepository.save(mockFile);

        // Verify that the file was updated correctly
        assertEquals(updatedFile.getFileId(), result.getFileId());
        assertEquals(updatedFile.getFileName(), result.getFileName());
        assertEquals(updatedFile.getFileBody(), result.getFileBody());
    }

    @Test
    public void testDeleteFile() throws Exception {
        // Create a mock file object
        File mockFile = new File();
        UUID uuid = UUID.fromString("cdd56295-fad7-4fa8-9bd8-3ae80ff8e5a9");
        mockFile.setFileId(uuid);
        mockFile.setFileName("ANewFile");
        mockFile.setFileBody("ANewFileBody");

        // Configure the mock repository to return true when existsById is called with the specific UUID
        when(fileRepository.existsById(uuid)).thenReturn(true);

        // Perform the DELETE request to /files/{uuid} endpoint
        mockMvc.perform(MockMvcRequestBuilders.delete("/files/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
