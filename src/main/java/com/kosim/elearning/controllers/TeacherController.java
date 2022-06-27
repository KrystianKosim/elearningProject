package com.kosim.elearning.controllers;

import com.kosim.elearning.models.dto.Teacher;
import com.kosim.elearning.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    ResponseEntity getAllTeachers() {
        return new ResponseEntity(teacherService.getAllTeachers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{email}")
    ResponseEntity getSingleTeacher(@PathVariable String email) {
        Optional<Teacher> foundedTeacher = teacherService.getSingleTeacher(email);
        if (foundedTeacher.isEmpty()) {
            return new ResponseEntity("Brak nauczyciela o podanym email " + email, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(foundedTeacher.get(), HttpStatus.OK);

    }

    @DeleteMapping("/{email}")
    ResponseEntity removeTeacher(@PathVariable String email) {
        boolean result = teacherService.removeTeacher(email);
        if (result) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Brak nauczyciela o podanym email " + email, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity addTeacher(@RequestBody Teacher teacher) {
        boolean result = teacherService.addTeacher(teacher);
        if (result) {
            return new ResponseEntity(teacher, HttpStatus.CREATED);
        }
        return new ResponseEntity("Nauczyciel o podanym email juz istnieje " + teacher.getEmail(), HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{email}")
    ResponseEntity editEntireTeacher(@PathVariable String email, @RequestBody Teacher teacher) {
        Optional<Teacher> foundedTeacher = teacherService.editEntireTeacher(email, teacher);
        if (foundedTeacher.isPresent()) {
            return new ResponseEntity(foundedTeacher.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Brak nauczyciela o podanym email " + email, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{email}")
    ResponseEntity editTeacher(@PathVariable String email, @RequestBody Teacher teacher) {
        Optional<Teacher> foundedTeacher = teacherService.editTeacher(email, teacher);
        if (foundedTeacher.isPresent()) {
            return new ResponseEntity(foundedTeacher.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Brak nauczyciela o podanym email " + email, HttpStatus.NOT_FOUND);
    }
}
