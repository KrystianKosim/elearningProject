//package com.kosim.elearning.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kosim.elearning.models.dto.LessonDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class LessonDtoControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    void shouldReturnSingleLesson() throws Exception {
//        int lessonId = 1;
//        mockMvc.perform(MockMvcRequestBuilders.get("/lessons/" + lessonId))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("22-04-2022"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherName").value("Malinowska"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Jan Nowak"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Matematyka"));
//    }
//
//    @Test
//    void shouldReturnMessageWhenLessonIdIsWrong() throws Exception {
//        int lessonId = 9;
//        mockMvc.perform(MockMvcRequestBuilders.get("/lessons/" + lessonId))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.content().string("Brak lekcji o ID: " + lessonId));
//    }
//
//    @Test
//    void shouldProperlyAddLesson() throws Exception {
//        LessonDto lessonToAdd = new LessonDto(5, "10-10-2020", 0, "Anna Lewandowska", "Geografia");
//        mockMvc.perform(MockMvcRequestBuilders.post("/lessons")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(lessonToAdd)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lessonId").value(5))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("10-10-2020"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherName").value("Nowak"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Anna Lewandowska"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Geografia"));
//    }
//
//    @Test
//    void shouldReturnMessageWhenLessonIsExist() throws Exception {
//        LessonDto lesson = new LessonDto(2, "10-12-2017", "Nowacka", "Jan Milewski", "Polski");
//        mockMvc.perform(MockMvcRequestBuilders.post("/lessons")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(lesson)))
//                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
//                .andExpect(MockMvcResultMatchers.content().string("Lekcja o podanym id juz istnieje " + lesson.getLessonId()));
//    }
//
//    @Test
//    void shouldReturnMessageWhenLessonIdIsWrongPut() throws Exception {
//        int lessonId = 8;
//        LessonDto lessonToEdit = new LessonDto(8, "10-12-2017", "Nowacka", "Jan Milewski", "Polski");
//        mockMvc.perform(MockMvcRequestBuilders.put("/lessons/" + lessonId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(lessonToEdit)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.content().string("Brak lekcji o podanym id " + lessonId));
//    }
//
//
//    @Test
//    void shouldEditEntireLesson() throws Exception {
//        int lessonId = 3;
//        LessonDto lessonToEdit = new LessonDto(8, "10-12-2017", "Nowacka", "Jan Milewski", "Polski");
//        mockMvc.perform(MockMvcRequestBuilders.put("/lessons/" + lessonId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(lessonToEdit)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lessonId").value(8))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("10-12-2017"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherName").value("Nowacka"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Jan Milewski"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Polski"));
//    }
//
//    @Test
//    void shouldEditLesson() throws Exception {
//        int lessonId = 3;
//        LessonDto lessonToEdit = new LessonDto(0, null, "Nowicka", null, null);
//        mockMvc.perform(MockMvcRequestBuilders.patch("/lessons/" + lessonId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(lessonToEdit)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lessonId").value(3))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("01-02-2022"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherName").value("Nowicka"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Adam Lewandowski"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.topic").value("Informatyka"));
//    }
//
//
//}