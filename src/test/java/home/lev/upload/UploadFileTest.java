package home.lev.upload;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
    http://spring.io/guides/gs/uploading-files/
    http://stackoverflow.com/questions/27050018/spring-file-upload-getting-expected-multiparthttpservletrequest-is-a-multipar

<html>
<body>
<form method="POST" enctype="multipart/form-data"
		action="http://localhost:8080/upload">
		File to upload: <input type="file" name="fileName"><br /> Name: <input
			type="text" name="name"><br /> <br /> <input type="submit"
			value="upload"> Press here to upload the file!
	</form>
</body>
</html>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(classes = ApplConfig.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/servlet-context.xml")
public class UploadFileTest {

    private String UPLOAD_ADDRESS="/upload";
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void fileUploadTest() throws Exception {

        String sentName = "fileName";
        String originalName="originalName";
        String fileContext = "MY_UPLOADED_FILE";

        MockMultipartFile file = new MockMultipartFile(sentName, originalName, null, fileContext.getBytes());

        MvcResult mvcResult= this.mockMvc.perform(fileUpload(UPLOAD_ADDRESS).file(file).param("name", sentName))
                .andExpect(status().isOk())
                .andExpect(content().string("uploaded success!" + " name:" + sentName))
                .andDo(print())
        .andReturn();
    }
    @Test
    public void nullUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("fileName","".getBytes());
        mockMvc.perform(fileUpload(UPLOAD_ADDRESS).file(file).param("name", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("file is big or empty"))
                .andDo(print())
        ;
    }

    @Test
    public void maxSizelimit() throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        while(stringBuilder.toString().getBytes().length<100){
            stringBuilder.append("1");
        }
        System.out.println("out="+stringBuilder.toString()+" size="+stringBuilder.toString().getBytes().length);
        MockMultipartFile file = new MockMultipartFile("fileName",stringBuilder.toString().getBytes());
        mockMvc.perform(fileUpload(UPLOAD_ADDRESS).file(file).param("name",""))
                .andExpect(status().isOk())
                .andExpect(content().string("file is big or empty"))
        ;
    }

    @Test
    public void getResponse() throws Exception {
        this.mockMvc.perform(get(UPLOAD_ADDRESS))
                .andExpect(status().isOk())
                .andExpect(content().string("please upload file to the same address"))
                .andDo(print())

        ;

    }
}

