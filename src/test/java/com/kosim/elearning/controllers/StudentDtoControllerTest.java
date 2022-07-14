package com.kosim.elearning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosim.elearning.models.dto.StudentDto;
import com.kosim.elearning.models.dto.TeacherDto;
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
class StudentDtoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnSingleStudent() throws Exception {
        String email = "jakub8899wp.pl";
        mockMvc.perform(MockMvcRequestBuilders.get("/students/" + email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jakub Nowicki"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher").value("Michał Leja"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(200));
    }

    @Test
    void shouldReturnMessageWhenEmailIsWrong() throws Exception {
        String email = "zenek@gmail.pl";
        mockMvc.perform(MockMvcRequestBuilders.get("/students/" + email))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Brak studenta o emailu " + email));
    }

//    @Test
//    void shouldProperlyAddStudent() throws Exception {
//        StudentDto studentDtoToAdd = StudentDto.builder()
//                .name("Janek")
//                .email("jano@gmail.com")
//                .teacher(TeacherDto.builder()
//                        .name("Anna")
//                        .email("Kowalewska")
//                        .build())
//                .build();
//        mockMvc.perform(MockMvcRequestBuilders.post("/students")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(studentDtoToAdd)))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//    }
//
//    @Test
//    void shouldRemoveStudent() throws Exception {
//        String studentEmailToRemove = "jakub8899wp.pl";
//        mockMvc.perform(MockMvcRequestBuilders.delete("/students/" + studentEmailToRemove))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//
//    @Test
//    void shouldReturnMessageWhenEmailWhileDeleteIsWrong() throws Exception {
//        String studentEmailToRemove = "janek3000";
//        mockMvc.perform(MockMvcRequestBuilders.delete("/students/" + studentEmailToRemove))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.content().string("Brak studenta o podanym email " + studentEmailToRemove));
//    }
//
//    @Test
//    void shouldReturnEntireUpdatedStudent() throws Exception {
//        String email = "jakub8899wp.pl";
//        StudentDto studentDto = StudentDto.builder()
//                .name("Jakub Nowacki")
//                .email("jakub8899wp.pl")
//                .teacher(TeacherDto.builder()
//                        .name("Anna Lewandowska")
//                        .email("email")
//                        .build())
//                .rate(123)
//                .build();
//        mockMvc.perform(MockMvcRequestBuilders.put("/students/" + email)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(studentDto)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jakub Nowacki"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jakub8899wp.pl"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher").value("Anna Lewandowska"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(123));
//    }
//
//    @Test
//    void shouldReturnUpdatedStudetn() throws Exception {
//        String email = "jakub8899wp.pl";
//        StudentDto studentDto = StudentDto.builder()
//                .teacher(TeacherDto.builder()
//                        .name("Monika Nowinska")
//                        .build())
//                .build();
//        mockMvc.perform(MockMvcRequestBuilders.patch("/students/" + email)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(studentDto)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jakub Nowicki"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jakub8899wp.pl"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher").value("Monika Nowinska"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(200));
//    }

}