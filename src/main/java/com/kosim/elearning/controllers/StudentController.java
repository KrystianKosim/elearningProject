package com.kosim.elearning.controllers;

import com.kosim.elearning.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping
    ResponseEntity addStudent(@RequestBody Student student) {
        if (students.stream().anyMatch(s -> s.getEmail().equals(student.getEmail()))) {
            return new ResponseEntity("Konto z takim email już istnieje", HttpStatus.BAD_REQUEST);
        }
        students.add(student);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity editStudent(@PathVariable String email, @RequestBody Student updatedStudent) {
        if (students.stream().noneMatch(student -> student.getEmail().equals(email))) {
            return new ResponseEntity("Brak studenta o danym emailu", HttpStatus.BAD_REQUEST);
        }
        Student student = students.stream().filter(s -> s.getEmail().equals(email)).findAny().get();
        student.setEmail(updatedStudent.getEmail());
        student.setName(updatedStudent.getName());
        student.setTeacher(updatedStudent.getTeacher());
        student.setRate(updatedStudent.getRate());
        return new ResponseEntity(student, HttpStatus.OK);
    }

    @PatchMapping("/{email}")
    public ResponseEntity editStudentPartially(@PathVariable String email, @RequestBody Student updatedStudent) {
        if (students.stream().noneMatch(student -> student.getEmail().equals(email))) {
            return new ResponseEntity("Brak studenta o danym emailu", HttpStatus.BAD_REQUEST);
        }
        Student student = students.stream().filter(s -> s.getEmail().equals(email)).findAny().get();
        Optional.ofNullable(updatedStudent.getEmail()).ifPresent(student::setEmail);
        Optional.ofNullable(updatedStudent.getRate()).ifPresent(student::setRate);
        Optional.ofNullable(updatedStudent.getName()).ifPresent(student::setName);
        Optional.ofNullable(updatedStudent.getTeacher()).ifPresent(student::setTeacher);
        return new ResponseEntity(student, HttpStatus.OK);
    }
}
