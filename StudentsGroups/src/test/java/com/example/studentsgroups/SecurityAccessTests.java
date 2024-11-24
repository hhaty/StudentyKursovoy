package com.example.studentsgroups;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SecurityAccessTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    void correctCredentials() throws Exception {
        mockMvc.perform(formLogin("/Login").user("admin").password("admin"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void badCredentials() throws Exception {
        mockMvc.perform(formLogin("/Login").user("admin1").password("admin1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/Login?error"));
    }

    @Test
    @WithAnonymousUser
    void guestMainAccess() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void guestLoginAccess() throws Exception {
        mockMvc.perform(get("/Login")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void guestRegistrationAcess() throws Exception {
        mockMvc.perform(get("/Registration")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void guestSpecialityAccess() throws Exception {
        mockMvc.perform(get("/Speciality"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/Login"));
    }

    @Test
    @WithAnonymousUser
    void guestQualificationAccess() throws Exception {
        mockMvc.perform(get("/Qualification"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/Login"));
    }

    @Test
    @WithAnonymousUser
    void guestFormEducationAccess() throws Exception {
        mockMvc.perform(get("/FormEducation"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/Login"));
    }

    @Test
    @WithAnonymousUser
    void guestGroupAccess() throws Exception {
        mockMvc.perform(get("/Group"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/Login"));
    }

    @Test
    @WithMockUser
    void userMainAccess() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userLoginAccess() throws Exception {
        mockMvc.perform(get("/Login")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void userRegistrationAccess() throws Exception {
        mockMvc.perform(get("/Registration")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void userSpecialityAccess() throws Exception {
        mockMvc.perform(get("/Speciality")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userQualificationAccess() throws Exception {
        mockMvc.perform(get("/Qualification")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userFormEducationAccess() throws Exception {
        mockMvc.perform(get("/FormEducation")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void userGroupAccess() throws Exception {
        mockMvc.perform(get("/Group")).andExpect(status().isOk());
    }

}
