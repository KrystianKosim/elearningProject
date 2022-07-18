package com.kosim.elearning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosim.elearning.models.dto.StudentDto;
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
        String email = "jankowalczyk@email";
        mockMvc.perform(MockMvcRequestBuilders.get("/students/" + email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jan Kowalczyk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(100));
    }

    @Test
    void shouldReturnMessageWhenEmailIsWrong() throws Exception {
        String email = "zenek@gmail.pl";
        mockMvc.perform(MockMvcRequestBuilders.get("/students/" + email))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Brak studenta o emailu " + email));
    }

    @Test
    void shouldProperlyAddStudent() throws Exception {
        StudentDto studentDtoToAdd = StudentDto.builder()
                .name("Janek")
                .email("jano@gmail.com")
                .teacherId(1)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDtoToAdd)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    //TODO usuwanie uzywanego studenta
    @Test
    void shouldRemoveStudent() throws Exception {
        String studentEmailToRemove = "marekmail@mail.com";
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/" + studentEmailToRemove))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void shouldReturnMessageWhenEmailWhileDeleteIsWrong() throws Exception {
        String studentEmailToRemove = "janek3000";
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/" + studentEmailToRemove))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Brak studenta o podanym email " + studentEmailToRemove));
    }


    @Test
    void shouldReturnEntireUpdatedStudent() throws Exception {
        String email = "jankowalczyk@email";
        StudentDto studentDto = StudentDto.builder()
                .name("Jakub Nowacki")
                .email("kowalczykJan@email")
                .teacherId(1)
                .rate(123)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.put("/students/" + email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jakub Nowacki"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("kowalczykJan@email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(123));
    }

    @Test
    void shouldReturnUpdatedStudetn() throws Exception {
        String email = "ArekTest@mail.com";
        StudentDto studentDto = StudentDto.builder()
                .teacherId(2)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.patch("/students/" + email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arek Testowy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ArekTest@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(200));
    }

}