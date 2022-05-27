package com.kosim.elearning.controllers;

import com.kosim.elearning.models.Lesson;
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
        if (!lessonService.addNewLesson(lesson)) {
            return new ResponseEntity("Lekcja o podanym id juz istnieje", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(lesson, HttpStatus.CREATED);
    }

    @PutMapping("/{lessonId}")
    ResponseEntity editEntireLesson(@PathVariable int lessonId, @RequestBody Lesson updateLesson) {
        Optional<Lesson> optionalLesson = lessonService.editEntireLesson(lessonId, updateLesson);
        if (optionalLesson.isEmpty()) {
            return new ResponseEntity("Brak lekcji o podanym id", HttpStatus.NOT_FOUND);
        }
        Lesson lesson = optionalLesson.get();
        return new ResponseEntity(lesson.toString(), HttpStatus.OK);
    }
    
    @PatchMapping("/{lessonId}")
    ResponseEntity editLesson(@PathVariable int lessonId, Lesson updateLesson){
        Optional<Lesson> optionalLesson = lessonService.editLesson(lessonId,updateLesson);
        if(optionalLesson.isPresent()){
            Lesson lesson = optionalLesson.get();
            return new ResponseEntity(lesson.toString(),HttpStatus.OK);
        }
        return new ResponseEntity("Brak lekcji o podanym ID",HttpStatus.NOT_FOUND);
    }
}
