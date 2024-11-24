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

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
public class ControllersTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser
    void workMainController() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Main"))
                .andExpect(content().string(containsString("Студенческие группы")));
    }

    @Test
    @WithMockUser
    void workSpecialityController() throws Exception{
        mockMvc.perform(get("/Speciality"))
                .andExpect(status().isOk())
                .andExpect(view().name("Speciality/Speciality"))
                .andExpect(content().string(containsString("Направления подготовки")));
    }

    @Test
    @WithMockUser
    void workQualificationController() throws Exception{
        mockMvc.perform(get("/Qualification"))
                .andExpect(status().isOk())
                .andExpect(view().name("Qualification/Qualification"))
                .andExpect(content().string(containsString("Квалификации")));
    }

    @Test
    @WithMockUser
    void workFormEducationController() throws Exception{
        mockMvc.perform(get("/FormEducation"))
                .andExpect(status().isOk())
                .andExpect(view().name("FormEducation/FormEducation"))
                .andExpect(content().string(containsString("Форма обучения")));
    }

    @Test
    @WithMockUser
    void workGroupController() throws Exception{
        mockMvc.perform(get("/Group"))
                .andExpect(status().isOk())
                .andExpect(view().name("Group/Group"))
                .andExpect(content().string(containsString("Студенческие группы")));
    }

    @Test
    @WithAnonymousUser
    void workRegistrationController() throws Exception{
        mockMvc.perform(get("/Registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("Registration"))
                .andExpect(content().string(containsString("Регистрация")));
    }

    @Test
    @WithAnonymousUser
    void workLoginController() throws Exception{
        mockMvc.perform(get("/Login"))
                .andExpect(status().isOk())
                .andExpect(view().name("Login"))
                .andExpect(content().string(containsString("Авторизация")));
    }
}
