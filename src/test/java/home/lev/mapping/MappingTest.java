package home.lev.mapping;

import home.lev.BasicTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MappingTest extends BasicTest {

    private final String URL="/mapping-class/";
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .alwaysExpect(status().isOk())
                .build();
    }


    @Test
    public void path() throws Exception {
        mockMvc.perform(get(URL+"path"))
                .andDo(print())
                .andExpect(content().string("mapped by path"))
        ;

    }

    @Test
    public void pathWildCard() throws Exception {
        mockMvc.perform(get(URL + "path/wildCard"))
                .andExpect(content().string("/mapping-class/path/wildCard"))
        ;
    }

    @Test
    public void requestParam() throws Exception {
        mockMvc.perform(get(URL+"/param?foo=Mike"))
                .andExpect(content().string("with param"))
                ;
    }
    @Test
    public void requestParamNo() throws Exception {
        mockMvc.perform(get(URL+"/param"))
                .andExpect(content().string("no param"))
                ;
    }

    @Test
    public void requestheader() throws Exception {
        mockMvc.perform(get(URL+"header").header("headerFoo", "myName"))
                .andExpect(content().string("with header"))
                ;
    }

    @Test
    public void requestheaderNo() throws Exception {
        mockMvc.perform(get(URL+"header").header("NO_headerFoo","NONE").header("Myheader","my header"))
                .andExpect(content().string("no header"))
        ;
    }


}
