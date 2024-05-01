package coursenotes.backend.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import coursenotes.backend.directory.Directory;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@WebMvcTest(Directory.class)
public class DirectoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectoryService directoryService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testCreateDirectory() throws Exception {
        Directory directory = new Directory();
        // Set your directory fields here
        when(directoryService.createDirectory(any(Directory.class))).thenReturn(directory);

        mockMvc.perform(post("/directories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(directory)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testReadDirectory() throws Exception {
        UUID directoryId = UUID.randomUUID();
        Directory directory = new Directory();
        when(directoryService.readDirectory(any(UUID.class))).thenReturn(directory);

        mockMvc.perform(post("/directories", directoryId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateDirectory() throws Exception {
        UUID directoryId = UUID.randomUUID();
        Directory directory = new Directory();// Set directory fields here

        mockMvc.perform(post("/directories/{id}", directoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(directory)))
                .andExpect(status().isOk());
        verify(directoryService, times(1)).updateDirectory(directoryId, directory);
    }

    @Test // Delete
    public void testDeleteDirectory() throws Exception {
        Directory directory = new Directory();

        String inputJson = mapper.writeValueAsString(directory);

        mockMvc.perform(post("/directories")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
    }

}
