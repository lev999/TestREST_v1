package home.lev.forms;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/servlet-context.xml")
@WebAppConfiguration
public class FormValidationTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;
    private String URL="/form/";
    @Before
    public void  setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysExpect(status().isOk())
                .alwaysDo(print())
                .build();
    }

    @Test
    public void check() throws Exception {
        mockMvc.perform(get(URL+"check"))
                .andExpect(content().string("check"))
        ;
    }
    @Test
    public void supplyNameAge() throws Exception {
        mockMvc.perform(post(URL+"supplyNameAge")

                        .param("name","My name")
                        .param("age", "22")
        )
                .andExpect(content().string("name=My name,age=22"))
        ;
    }

    @Test
    public void supplyNameAgeError() throws Exception {
        mockMvc.perform(post(URL+"supplyNameAge")
                        .param("name","")
                        .param("age","122")
        )
                .andExpect(content().string(Matchers.startsWith("error")))
        ;
    }



}
