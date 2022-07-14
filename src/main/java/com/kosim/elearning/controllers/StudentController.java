package com.kosim.elearning.controllers;

import com.kosim.elearning.models.dto.StudentDto;
import com.kosim.elearning.models.entity.StudentEntity;
import com.kosim.elearning.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity getSingleStudent(@PathVariable String email) {
        Optional<StudentDto> student = studentService.getSingleStudent(email);
        if (student.isEmpty()) {
            return new ResponseEntity("Brak studenta o emailu " + email, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(student.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    ResponseEntity removeStudent(@PathVariable String email) {
        boolean result = studentService.removeStudent(email);
        if (result) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Brak studenta o podanym email " + email, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity addStudent(@RequestBody StudentDto studentDto) {
        if (studentService.addStudent(studentDto)) {
            return new ResponseEntity("Dodano studenta", HttpStatus.CREATED);
        }
        return new ResponseEntity("Student o podanym Email juz istnieje " + studentDto.getEmail(), HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{email}")
    ResponseEntity editEntireStudent(@PathVariable String email, @RequestBody StudentDto studentDto) {
        Optional<StudentDto> studentOptional = studentService.editEntireStudent(email, studentDto);
        if (studentOptional.isPresent()) {
            return new ResponseEntity(studentOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Brak studenta o podanym email " + email, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{email}")
    ResponseEntity editStudent(@PathVariable String email, @RequestBody StudentDto studentDto) {
        Optional<StudentDto> foundedStudent = studentService.editStudent(email, studentDto);
        if (foundedStudent.isPresent()) {
            return new ResponseEntity(foundedStudent.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Brak studenta o podanym email " + email, HttpStatus.NOT_FOUND);
    }


}
