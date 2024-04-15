package com.uala.examen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uala.examen.dto.user.request.CreateUserRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    @Test
    public void getUserTest_ok() throws Exception {

        this.mockMvc.perform(get("/api/user/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void getUserTest_badRequest() throws Exception {

        this.mockMvc.perform(get("/api/user/12")
                        .contentType("application/json"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message", is("Usuario '12' no encontrado")))
                .andExpect(jsonPath("$.code", is(404)))
                .andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void createUserTest_ok() throws Exception {
        CreateUserRequestDTO newUser = new CreateUserRequestDTO("testUser");

        this.mockMvc.perform(post("/api/user")
                        .content(objectmapper.writeValueAsString(newUser))
                        .contentType("application/json"))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void createUserTest_badRequest() throws Exception {
        CreateUserRequestDTO newUser = new CreateUserRequestDTO("");

        this.mockMvc.perform(post("/api/user")
                        .content(objectmapper.writeValueAsString(newUser))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("username: must not be empty. ")))
                .andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void followUserTest_ok() throws Exception {

        this.mockMvc.perform(post("/api/user/1/follow/10")
                        .contentType("application/json"))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();

        //verifico.
        this.mockMvc.perform(get("/api/user/1")
                        .contentType("application/json"))
                .andExpect(jsonPath("$.following",is(9)))
                .andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void followUserTest_badRequest() throws Exception {

        this.mockMvc.perform(post("/api/user/1/follow/1")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("No puedes seguirte a ti mismo")))
                .andReturn()
                .getResponse().getContentAsString();
    }
}
