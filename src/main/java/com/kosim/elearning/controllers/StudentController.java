package com.kosim.elearning.controllers;

import com.kosim.elearning.models.Student;
import com.kosim.elearning.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    ResponseEntity getAllStudents() {
        return new ResponseEntity(studentService.getAllStudents(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{email}")
    ResponseEntity getSingleStudent(@PathVariable String email){
        Optional<Student> student = studentService.getSingleStudent(email);
        if(student.isEmpty()){
            return new ResponseEntity("Brak studenta o emailu " + email, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(student.get(),HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity addStudent(@RequestBody Student student){
        if(studentService.addStudent(student)){
            return new ResponseEntity("Dodano studenta", HttpStatus.CREATED);
        }
        return new ResponseEntity("Student o podanym Email juz istnieje", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{email}")
    ResponseEntity editEntireStudent(@PathVariable String email,@RequestBody Student student){
        Optional<Student> studentOptional = studentService.editEntireStudent(email,student);
        if(studentOptional.isPresent()){
            Student updatedStudent = studentOptional.get();
            return new ResponseEntity(updatedStudent.toString(),HttpStatus.OK);
        }
        return new ResponseEntity("Brak studenta o podanym email " + email, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{email}")
    ResponseEntity editStudent(@PathVariable String email,@RequestBody Student student){
        Optional<Student> foundedStudent = studentService.editStudent(email,student);
        if(foundedStudent.isPresent()){
            Student updatedStudent = foundedStudent.get();
            return new ResponseEntity(updatedStudent.toString(),HttpStatus.OK);
        }
        return new ResponseEntity("Brak studenta o podanym email " + email, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{email}")
    ResponseEntity removeStudent(@PathVariable String email){
        if(studentService.removeStudent(email)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Brak studenta o podanym email " + email, HttpStatus.NOT_FOUND);
    }
}
