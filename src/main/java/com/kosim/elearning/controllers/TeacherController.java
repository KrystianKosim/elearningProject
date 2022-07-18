package com.kosim.elearning.controllers;

import com.kosim.elearning.exceptions.NoTeacherWithGivenEmailException;
import com.kosim.elearning.models.dto.TeacherDto;
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
    ResponseEntity getSingleTeacher(@PathVariable String email) throws NoTeacherWithGivenEmailException {
        Optional<TeacherDto> foundedTeacher = teacherService.getSingleTeacher(email);
        if (foundedTeacher.isEmpty()) {
            throw new NoTeacherWithGivenEmailException("Brak nauczyciela o podanym email " + email, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(foundedTeacher.get(), HttpStatus.OK);

    }

    @DeleteMapping("/{email}")
    ResponseEntity removeTeacher(@PathVariable String email) throws NoTeacherWithGivenEmailException {
        boolean result = teacherService.removeTeacher(email);
        if (result) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        throw new NoTeacherWithGivenEmailException("Brak nauczyciela o podanym email " + email, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity addTeacher(@RequestBody TeacherDto teacherDto) {
        boolean result = teacherService.addTeacher(teacherDto);
        if (result) {
            return new ResponseEntity(teacherDto, HttpStatus.CREATED);
        }
        return new ResponseEntity("Nauczyciel o podanym email juz istnieje " + teacherDto.getEmail(), HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{email}")
    ResponseEntity editEntireTeacher(@PathVariable String email, @RequestBody TeacherDto teacherDto) throws NoTeacherWithGivenEmailException {
        Optional<TeacherDto> foundedTeacher = teacherService.editEntireTeacher(email, teacherDto);
        if (foundedTeacher.isPresent()) {
            return new ResponseEntity(foundedTeacher.get(), HttpStatus.OK);
        }
        throw new NoTeacherWithGivenEmailException("Brak nauczyciela o podanym email " + email, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{email}")
    ResponseEntity editTeacher(@PathVariable String email, @RequestBody TeacherDto teacherDto) throws NoTeacherWithGivenEmailException {
        Optional<TeacherDto> foundedTeacher = teacherService.editTeacher(email, teacherDto);
        if (foundedTeacher.isPresent()) {
            return new ResponseEntity(foundedTeacher.get(), HttpStatus.OK);
        }
        throw new NoTeacherWithGivenEmailException("Brak nauczyciela o podanym email " + email, HttpStatus.NOT_FOUND);
    }
}
