package com.kosim.elearning.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
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
public class TeacherDtoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnSingleTeacher() throws Exception {
        String email = "halinamalinowska@o2.pl";
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/" + email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Halina Malinowska"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("halinamalinowska@o2.pl"));
    }

    @Test
    void shouldReturnMessageWhenEmailIsWrong() throws Exception {
        String email = "blednyemail";
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/" + email))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Brak nauczyciela o podanym email " + email));
    }

    @Test
    void shouldRemoveTeacher() throws Exception {
        String email = "annakowalska@wp.pl";
        mockMvc.perform(MockMvcRequestBuilders.delete("/teacher/" + email))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void shouldAddTeacher() throws Exception {
        TeacherDto teacherDto = new TeacherDto("Anna Nowa", "nowaAnna@mail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameAndSurname").value("Anna Nowa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("nowaAnna@mail.com"));
    }

    @Test
    void shouldEditEntireTeacher() throws Exception {
        String email = "zalewski@o2.pl";
        TeacherDto teacherDto = new TeacherDto("Mariusz Zalewski", "zalewski@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.put("/teacher/" + email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameAndSurname").value("Mariusz Zalewski"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("zalewski@gmail.com"));
    }

    @Test
    void shouldEditTeacher() throws Exception {
        String email = "halinamalinowska@o2.pl";
        TeacherDto teacherDto = new TeacherDto(null, "halinamalinowska@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.patch("/teacher/" + email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameAndSurname").value("Halina Malinowska"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("halinamalinowska@gmail.com"));
    }
}
