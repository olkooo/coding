package com.verygood.security.coding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verygood.security.coding.aliases.AliasCreateRequest;
import com.verygood.security.coding.aliases.AliasesController;
import com.verygood.security.coding.aliases.AliasesServiceImpl;
import com.verygood.security.coding.dao.MockAliasRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AliasesController.class, AliasesServiceImpl.class, MockAliasRepository.class})
@AutoConfigureMockMvc
public class TestAliasRESTController {
    @Autowired
    private MockMvc mvc;

    @Test
    public void aliasCreated() throws Exception {
        AliasCreateRequest request = new AliasCreateRequest();
        request.setSecret("1111 2222 3333 4444");
        mvc.perform(
                MockMvcRequestBuilders.post("/aliases")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(request))
        )
                .andExpect(status().isCreated());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
