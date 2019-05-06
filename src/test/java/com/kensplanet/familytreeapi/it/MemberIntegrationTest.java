package com.kensplanet.familytreeapi.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.repository.MemberRepository;

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
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MemberIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void unauthorizedAccessShouldThrow401() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void userShouldBeAllowedToFetchMembers() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(memberRepository.findAll().size())));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void adminShouldBeAllowedToFetchMembers() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(memberRepository.findAll().size())));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void getMemberReturnsAppropriateMember() throws Exception {
        mockMvc.perform(get("/members/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(1));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void creatingAndDeletingMemberWorks() throws Exception {
        Integer previousMemberSize = memberRepository.findAll().size();

        // Create a new member.
        Member member = new Member();
        member.setName("Prince Nicolas, Duke of Ångermanland");
        member.setBirthPlace("Danderyd, Sweden");
        member.setBirthYear(2015);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Prince Nicolas, Duke of Ångermanland"));

        assertThat(memberRepository.findAll().size()).isEqualTo(previousMemberSize + 1);

        // Delete this member
        member = memberRepository.findByName("Prince Nicolas, Duke of Ångermanland");
        mockMvc.perform(delete("/members/" + member.getMemberId()))
                .andExpect(status().isOk());

        assertThat(memberRepository.findAll().size()).isEqualTo(previousMemberSize);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void updatingMemberWorks() throws Exception {
        Member member = memberRepository.findById(1).get();
        member.setBirthPlace("Germany");
        member.setSpouses(null);
        member.setParent(null);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthPlace").value("Germany"));
    }
}
