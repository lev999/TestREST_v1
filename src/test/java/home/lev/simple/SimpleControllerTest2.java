package home.lev.simple;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by pc-users on 7/14/2015.
 */
public class SimpleControllerTest2 {


    @Test
    public void getResponse() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new SimpleController())

                .build();
        mockMvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN))
                .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string("Hello world"))
        ;
    }
}
