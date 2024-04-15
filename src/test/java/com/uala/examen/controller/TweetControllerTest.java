package com.uala.examen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uala.examen.dto.tweet.request.CreateTweetRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    @Test
    public void createTweetTest_ok() throws Exception {
        CreateTweetRequestDTO requestDTO = new CreateTweetRequestDTO("test");

        this.mockMvc.perform(post("/api/tweet/user/1")
                        .content(objectmapper.writeValueAsString(requestDTO))
                        .contentType("application/json"))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void createTweetTest_badRequest() throws Exception {
        CreateTweetRequestDTO requestDTO = new CreateTweetRequestDTO("");

        this.mockMvc.perform(post("/api/tweet/user/1")
                        .content(objectmapper.writeValueAsString(requestDTO))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(400)))
                .andExpect(jsonPath("$.message", is("message: must not be empty. ")))
                .andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void getTimelineByUserIdTest_ok() throws Exception {

        this.mockMvc.perform(get("/api/tweet/timeline/user/1?page=0&size=5")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(5)))
                .andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void getTimelineByUserIdTest_badRequest() throws Exception {

        this.mockMvc.perform(get("/api/tweet/timeline/user/12?page=0&size=5")
                        .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(404)))
                .andExpect(jsonPath("$.message", is("Usuario '12' no encontrado")))
                .andReturn()
                .getResponse().getContentAsString();
    }
}
