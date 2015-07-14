package home.lev.mapping;

import home.lev.BasicTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MappingTest extends BasicTest {

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .alwaysExpect(status().isOk())
                .build();
    }


    @Test
    public void path() throws Exception {
        mockMvc.perform(get("/mapping-class/path"))
                .andDo(print())
                .andExpect(content().string("mapped by path"))
        ;

    }

    @Test
    public void pathWildCard() throws Exception {
        mockMvc.perform(get("/mapping-class/path/wildCard"))
                .andExpect(content().string("/mapping-class/path/wildCard"))
        ;
    }


}
