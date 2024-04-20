package org.itmo;

import org.itmo.application1.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCat() throws Exception {
        mockMvc.perform(get("/cat/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createCat() throws Exception {

        String content = "{\"name\": \"boba\",\"birthday\": \"2020-05-03\",\"breed\": \"BRITAIN\",\"color\": \"BLACK\",\"masterId\": 1}";

        mockMvc.perform(post("/cat/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getMaster() throws Exception {
        mockMvc.perform(get("/master/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createMaster() throws Exception {

        String content = "{\"birthday\": \"2004-12-20\", \"name\": \"dima\"}";

        mockMvc.perform(post("/master/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print()).andExpect(status().isOk());
    }
}
