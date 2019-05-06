package com.kensplanet.familytreeapi.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kensplanet.familytreeapi.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ExceptionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void gettingUnknownMemberIdThrows400() throws Exception {
        mockMvc.perform(get("/members/1000"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void deletingUnknownMemberIdThrows400() throws Exception {
        mockMvc.perform(delete("/members/1000"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void deletingRelatedMemberIdThrows403() throws Exception {
        mockMvc.perform(delete("/members/1"))
                .andExpect(status().isForbidden());
    }
}
