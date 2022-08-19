package com.kosim.elearning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosim.elearning.models.dto.LessonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LessonDtoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnSingleLesson() throws Exception {
        int lessonId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/lessons/" + lessonId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2022-02-13T20:15:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", hasItem(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Historia"));
    }

    @Test
    void shouldReturnMessageWhenLessonIdIsWrong() throws Exception {
        int lessonId = 9;
        mockMvc.perform(MockMvcRequestBuilders.get("/lessons/" + lessonId))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Brak lekcji o ID"));
    }

    @Test
    void shouldProperlyAddLesson() throws Exception {
        LessonDto lessonDto = LessonDto.builder()
                .date(LocalDateTime.of(2022, 10, 20, 10, 30))
                .teacherId(1L)
                .students(List.of(1L, 2L))
                .topic("Matematyka")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2022-10-20T10:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", hasItems(1, 2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Matematyka"));
    }


    @Test
    void shouldReturnMessageWhenLessonIdIsWrongPut() throws Exception {
        int lessonId = 8;
        LessonDto lessonDto = LessonDto.builder()
                .date(LocalDateTime.of(2020, 12, 10, 11, 30))
                .teacherId(2L)
                .students(List.of(1L))
                .topic("Polski")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.put("/lessons/" + lessonId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonDto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Brak lekcji o ID"));
    }


    @Test
    void shouldEditEntireLesson() throws Exception {
        int lessonId = 2;
        LessonDto lessonDto = LessonDto.builder()
                .date(LocalDateTime.of(2022, 11, 9, 8, 45))
                .topic("Chemia")
                .teacherId(1L)
                .students(List.of(3L))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/lessons/" + lessonId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2022-11-09T08:45:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", hasItem(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Chemia"));
    }

    @Test
    void shouldEditLesson() throws Exception {
        int lessonId = 3;
        LessonDto lessonDto = LessonDto.builder()
                .date(LocalDateTime.of(2020, 11, 11, 11, 11))
                .topic("Biologia")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.patch("/lessons/" + lessonId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2020-11-11T11:11:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.students", hasItems(1, 2, 3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Biologia"));
    }
}