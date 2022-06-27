package com.kosim.elearning.controllers;

import com.kosim.elearning.models.dto.Lesson;
import com.kosim.elearning.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    ResponseEntity<List<Lesson>> getAllLessons() {
        return new ResponseEntity<>(lessonService.getAllLessons(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{lessonId}")
    ResponseEntity<Lesson> getSingleLesson(@PathVariable int lessonId) {
        Optional<Lesson> lesson = lessonService.getSingleLesson(lessonId);
        if (lesson.isEmpty()) {
            return new ResponseEntity("Brak lekcji o ID: " + lessonId, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{lessonId}")
    ResponseEntity removeLesson(@PathVariable int lessonId) {
        boolean result = lessonService.removeLessonById(lessonId);
        if (result) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity("Brak lekcji o podanym ID: " + lessonId, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    ResponseEntity<Lesson> addLesson(@RequestBody Lesson lesson) {
        boolean result = lessonService.addNewLesson(lesson);
        if (result) {
            return new ResponseEntity<>(lesson, HttpStatus.CREATED);
        }
        return new ResponseEntity("Lekcja o podanym id juz istnieje " + lesson.getLessonId(), HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{lessonId}")
    ResponseEntity editEntireLesson(@PathVariable int lessonId, @RequestBody Lesson updateLesson) {
        Optional<Lesson> foundedLesson = lessonService.editEntireLesson(lessonId, updateLesson);
        if (foundedLesson.isPresent()) {
            return new ResponseEntity(foundedLesson.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Brak lekcji o podanym id " + lessonId, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/{lessonId}")
    ResponseEntity editLesson(@PathVariable int lessonId, @RequestBody Lesson updateLesson) {
        Optional<Lesson> foundedLesson = lessonService.editLesson(lessonId, updateLesson);
        if (foundedLesson.isPresent()) {
            return new ResponseEntity(foundedLesson.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Brak lekcji o podanym ID " + lessonId, HttpStatus.NOT_FOUND);
    }
}
