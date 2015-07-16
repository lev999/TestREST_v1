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

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/servlet-context.xml")
@WebAppConfiguration
public class StandartRequestTest {

    private final String URL="/data/standartRequest/";
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .alwaysExpect(status().isOk())
                .build();
    }

    @Test
    public void check() throws Exception {
        mockMvc.perform(get(URL+"check"))
                .andExpect(content().string("check"))
        ;
    }

    @Test
    public void PrincipalLocale() throws Exception {
        mockMvc.perform(get(URL+"PrincipalLocale"))
                .andExpect(content().string(startsWith("PrincipalLocale")))
        ;

    }

    @Test
    public void readWithReader() throws Exception {
        mockMvc.perform(post(URL+"readWithReader")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("foo".getBytes())
        )
                .andExpect(content().string("readWithReader:foo"))
        ;
    }
    @Test
    public void readWithInputStream() throws Exception {
        mockMvc.perform(post(URL+"readWithInputStream")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("foo".getBytes())
        )
                .andExpect(content().string("readWithInputStream:foo"))
        ;
    }


}
