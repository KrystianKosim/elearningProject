package com.kosim.elearning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosim.elearning.models.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class LessonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnSingleLesson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/lessons/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("22-04-2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherName").value("Malinowska"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Jan Nowak"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Matematyka"));
    }

    @Test
    void shouldReturnMessageWhenLessonIdIsWrong() throws Exception {
        int lessonId = 9;
        mockMvc.perform(MockMvcRequestBuilders.get("/lessons/" + lessonId))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Brak lekcji o ID: " + lessonId));
    }

    @Test
    void shouldProperlyAddLesson() throws Exception {
        Lesson lessonToAdd = new Lesson(5, "10-10-2020", "Nowak", "Anna Lewandowska", "Geografia");
        mockMvc.perform(MockMvcRequestBuilders.post("/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Dodano lekcje " + lessonToAdd));
    }

    @Test
    void shouldReturnMessageWhenLessonIsExist() throws Exception {
        Lesson lessonToAdd = new Lesson(2, "10-12-2017", "Nowacka", "Jan Milewski", "Polski");
        mockMvc.perform(MockMvcRequestBuilders.post("/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonToAdd)))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andExpect(MockMvcResultMatchers.content().string("Lekcja o podanym id juz istnieje"));
    }

    @Test
    void shouldReturnMessageWhenLessonIdIsWrongPut() throws Exception {
        int lessonId = 8;
        Lesson lessonToEdit = new Lesson(8, "10-12-2017", "Nowacka", "Jan Milewski", "Polski");
        mockMvc.perform(MockMvcRequestBuilders.put("/lessons/" + lessonId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonToEdit)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Brak lekcji o podanym id"));
    }


    @Test
    void shouldEditEntireLesson() throws Exception {
        int lessonId = 3;
        Lesson lessonToEdit = new Lesson(8, "10-12-2017", "Nowacka", "Jan Milewski", "Polski");
        mockMvc.perform(MockMvcRequestBuilders.put("/lessons/" + lessonId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lessonToEdit)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(lessonToEdit.toString()));
    }

    @Test
    void shouldEditLesson() throws Exception{
        int lessonId = 3;
        Lesson lesson = new Lesson(3,"01-02-2022", "Wierzbicka", "Adam Lewandowski", "Informatyka");
        Lesson lessonToEdit = new Lesson(0,null,"Nowicka",null,null);
        mockMvc.perform(MockMvcRequestBuilders.patch("/lessons/" + lessonId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(lessonToEdit)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(lesson.toString()));
    }


}