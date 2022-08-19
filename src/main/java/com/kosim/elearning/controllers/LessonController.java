package com.kosim.elearning.controllers;

import com.kosim.elearning.exceptions.NoLessonWithGivenIdException;
import com.kosim.elearning.models.dto.LessonDto;
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
    ResponseEntity<List<LessonDto>> getAllLessons() {
        return new ResponseEntity<>(lessonService.getAllLessons(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{lessonId}")
    ResponseEntity<LessonDto> getSingleLesson(@PathVariable Long lessonId) throws NoLessonWithGivenIdException {
        Optional<LessonDto> lesson = lessonService.getSingleLesson(lessonId);
        if (lesson.isEmpty()) {
            throw new NoLessonWithGivenIdException("Brak lekcji o ID", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{lessonId}")
    ResponseEntity removeLesson(@PathVariable Long lessonId) throws NoLessonWithGivenIdException {
        boolean result = lessonService.removeLessonById(lessonId);
        if (result) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        throw new NoLessonWithGivenIdException("Brak lekcji o ID", HttpStatus.NOT_FOUND);

    }

    @PostMapping
    ResponseEntity<LessonDto> addLesson(@RequestBody LessonDto lesson) {
        boolean result = lessonService.addNewLesson(lesson);
        if (result) {
            return new ResponseEntity<>(lesson, HttpStatus.CREATED);
        }
        return new ResponseEntity("Lekcja o podanym id juz istnieje ", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{lessonId}")
    ResponseEntity editEntireLesson(@PathVariable Long lessonId, @RequestBody LessonDto updateLesson) throws NoLessonWithGivenIdException {
        Optional<LessonDto> foundedLesson = lessonService.editEntireLesson(lessonId, updateLesson);
        if (foundedLesson.isPresent()) {
            return new ResponseEntity(foundedLesson.get(), HttpStatus.OK);
        }
        throw new NoLessonWithGivenIdException("Brak lekcji o ID", HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/{lessonId}")
    ResponseEntity editLesson(@PathVariable Long lessonId, @RequestBody LessonDto updateLesson) throws NoLessonWithGivenIdException {
        Optional<LessonDto> foundedLesson = lessonService.editLesson(lessonId, updateLesson);
        if (foundedLesson.isPresent()) {
            return new ResponseEntity(foundedLesson.get(), HttpStatus.OK);
        }
        throw new NoLessonWithGivenIdException("Brak lekcji o ID", HttpStatus.NOT_FOUND);
    }
}
