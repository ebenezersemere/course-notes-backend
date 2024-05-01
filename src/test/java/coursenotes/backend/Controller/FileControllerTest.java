package coursenotes.backend.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import coursenotes.backend.file.File;
import coursenotes.backend.file.FileRepository;
import coursenotes.backend.file.FileService;
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

@RunWith(SpringRunner.class)
@WebMvcTest(File.class)
public class FileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private File mockfile;
    private FileRepository fileRepository;
    private FileService fileService;

    @Autowired
    private ObjectMapper mapper;
    private List<File> fileList;

    // Create
    @Test
    public void testCreateFile() throws Exception {
        File file = new File();
        UUID uuid = UUID.randomUUID();
        file.setFileId(uuid);
        file.setFileName("Algorithms");
        file.setDateCreated(LocalDateTime.parse("2020-01-01 13:14"));
        file.setFileBody("blablabla");
        file.setDateModified(LocalDateTime.now());
        file.setIsShared(false);


        String inputJson = mapper.writeValueAsString(file);

        mockMvc.perform(post("/book")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test // Read by id
    public void testReadFile() throws Exception {
        File file = new File();
        UUID uuid = UUID.randomUUID();
        file.setFileId(uuid);
        file.setFileName("Algorithms");
        file.setDateCreated(LocalDateTime.parse("2020-01-01 13:14"));
        file.setFileBody("blablabla");
        file.setDateModified(LocalDateTime.now());
        file.setIsShared(false);

        String inputJson = mapper.writeValueAsString(file);

        mockMvc.perform(MockMvcRequestBuilders.get("/files/5")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test // Update
    public void testUpdateFile() throws Exception {
        File file = new File();
        UUID uuid = UUID.randomUUID();
        file.setFileId(uuid);
        file.setFileName("Algorithms");
        file.setDateCreated(LocalDateTime.parse("2020-01-01 13:14"));
        file.setFileBody("blablabla");
        file.setDateModified(LocalDateTime.now());
        file.setIsShared(false);

        String inputJson = mapper.writeValueAsString(file);

        mockMvc.perform(MockMvcRequestBuilders.put("/files/5")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test // Delete
    public void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/files/5"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }



}
