package com.kosim.elearning.controllers;

import com.kosim.elearning.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
class StudentController {
    private List<Student> students = new ArrayList<>();

    @PostConstruct
    void init() {
        students.add(new Student("Mirosław Kowalski", "mirek@gmail.com", "Michał Leja", 200));
        students.add(new Student("Jakub Nowicki", "jakub8899wp.pl", "Michał Leja", 200));
    }

    @GetMapping
    ResponseEntity getAllStudents() {
        return new ResponseEntity(students, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{email}")
    ResponseEntity getSingleStudent(@PathVariable String email) {
        return students.stream()
                .filter(s -> s.getEmail().equals(email))
                .findAny()
                .map(student -> new ResponseEntity(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak studenta o emailu " + email, HttpStatus.NOT_FOUND));
    }

}
