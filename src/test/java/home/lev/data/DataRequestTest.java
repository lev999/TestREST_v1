package home.lev.data;

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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/servlet-context.xml")
public class DataRequestTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    private final String URL="/data/";

    MockMvc mockMvc;

    @Before
    public void setUp(){
         mockMvc = MockMvcBuilders.standaloneSetup(new DataRequest())
                .alwaysDo(print())
                .alwaysExpect(status().isOk())
                .build();
    }

    @Test
    public void oneParamRequest() throws Exception {
        mockMvc.perform(get(URL+"oneParamRequest").param("oneParam","one param it's me"))
                .andExpect(content().string("one param it's me"))
                ;
    }
    @Test
    public void manyParamRequest() throws Exception {

        mockMvc.perform(get(URL+"manyParamRequest")
        .param("one", "1")
                        .param("two", "2")
                        .param("three","3")
        )
                .andExpect(content().string("123"))
                ;
    }
    @Test
    public void pathParamRequest() throws Exception {
        mockMvc.perform(get(URL+"pathParamRequest/MyAss/Kiss"))
                .andExpect(content().string("Kiss MyAss"))
                ;
    }
}
