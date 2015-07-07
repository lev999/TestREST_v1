package home;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import home.lev.Config.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = home.lev.Config.ApplConfig.class)
public class ControllerTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMVC;

    @Before
    public void setup(){
        this.mockMVC = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void pathName() throws Exception {
        String name= "Vasia";
        this.mockMVC.perform(get("/student/"+name) )
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.name").value(name))
        ;
    }

    @Test
    public void noneName() throws Exception {
        this.mockMVC.perform(get("/student/"))
                .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.name").value("noneName"))
        ;

    }
}
