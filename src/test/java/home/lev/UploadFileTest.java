package home.lev;

import home.lev.Config.ApplConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplConfig.class)
public class UploadFileTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void fileUploadTest() throws Exception {

        String sentName = "file";
        String originalName="originalName";
        String fileContext = "MY_UPLOADED_FILE";

//----------------WHY can not change "sentName" field?-----------
        MockMultipartFile file = new MockMultipartFile(sentName, originalName, null, fileContext.getBytes());

        this.mockMvc.perform(fileUpload("/upload/").file(file))
                .andExpect(status().isOk())
                .andExpect(model().attribute("sentName", sentName))
                .andExpect(model().attribute("originalName", originalName))
                .andExpect(model().attribute("fileContext",fileContext))

        ;
    }

}
