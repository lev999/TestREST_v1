package home.lev.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/servlet-context.xml")
public class DataRequestTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    private final String URL="/data/";

    MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
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
                        .param("three", "3")
        )
                .andExpect(content().string("123"))
        ;
    }
    @Test
    public void pathParamRequest() throws Exception {
        mockMvc.perform(get(URL + "pathParamRequest/MyAss/Kiss"))
                .andExpect(content().string("Kiss MyAss"))
        ;
    }

    @Test
    public void matrixRequest() throws Exception {
        mockMvc.perform(get(URL+"pets/42;q=11;r=22"))
                .andExpect(content().string("1122"))
        ;
    }

    @Test
    public void matrixWithTwoPathVar() throws Exception {
        mockMvc.perform(get(URL+"matrixWithTwoPathVar/a1;a=3/a2;a=4"))
                .andExpect(content().string("34"))

        ;
    }

    @Test
    public void requestParamOptional() throws Exception {

        mockMvc.perform(post(URL+"requestParamOptional"))
                .andExpect(content().string("requestParamOptional"))
        ;
        mockMvc.perform(post(URL+"requestParamOptional").param("param", "me"))
                .andExpect(content().string("requestParamOptional-me"))
        ;
    }

    @Test
    public void byHeader() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(get(URL+"byHeader").header("header", MediaType.ALL))
                .andExpect(content().string("*/*"))
        ;
        mockMvc.perform(get(URL + "byHeader"))
                .andExpect(content().string("none"))
        ;
    }

    @Test
    public void byBody() throws Exception {
        mockMvc.perform(get(URL+"byBody")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("My body".getBytes())
        )
                .andExpect(content().string("My body"))
        ;
        mockMvc.perform(get(URL+"byBody"))
                .andExpect(content().string("none"))
        ;
    }

    @Test
    public void byBodyAndHeader() throws Exception {
        String header="My_header";
        String body="My body";

        mockMvc.perform(post(URL+"byBodyAndHeader")
                        .header("header",header)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(body.getBytes())
        )
                .andExpect(content().string(header+" "+body))
        ;
    }


}
