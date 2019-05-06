package com.kensplanet.familytreeapi.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SpringSecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginPageShouldHavePublicAccess() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void unauthorizedAccessShouldThrow401() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/members/1"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/members"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete("/members/1"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/parents"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/parents"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/parents/addParent"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/familyTree/1"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/userContext"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void userCannotModifyData() throws Exception {
        mockMvc.perform(post("/members"))
                .andExpect(status().isForbidden());

        mockMvc.perform(delete("/members/1"))
                .andExpect(status().isForbidden());

        mockMvc.perform(post("/parents"))
                .andExpect(status().isForbidden());

        mockMvc.perform(post("/parents/addParent"))
                .andExpect(status().isForbidden());

        mockMvc.perform(post("/userContext"))
                .andExpect(status().isForbidden());
    }
}
