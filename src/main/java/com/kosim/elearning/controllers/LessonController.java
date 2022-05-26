package com.kosim.elearning.controllers;

import com.kosim.elearning.models.Lesson;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/lessons")
public class LessonController {
    private List<Lesson> lessons = new ArrayList<>();

    @PostConstruct
    void init() {
        lessons.add(new Lesson(1, "22-04-2022", "Malinowska", "Jan Nowak", "Matematyka"));
        lessons.add(new Lesson(2, "12-03-2022", "Kowalska", "Adrian Kowalczyk", "Polski"));
        lessons.add(new Lesson(3, "01-02-2022", "Wierzbicka", "Adam Lewandowski", "Informatyka"));
        lessons.add(new Lesson(4, "05-05-2022", "Szymanski", "Marcin Nowicki", "WF"));
    }

    @GetMapping
    ResponseEntity getAllLessons() {
        return new ResponseEntity(lessons, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{lessonId}")
    ResponseEntity getSingleLesson(@PathVariable int lessonId) {
        return lessons.stream()
                .filter(lesson -> lesson.getLessonId() == lessonId)
                .findAny().map(lesson -> new ResponseEntity(lesson, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("Brak lekcji o ID: " + lessonId, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{lessonId}")
    ResponseEntity deleteLesson(@PathVariable int lessonId) {
        Optional<Lesson> toRemove = lessons.stream()
                .filter(lesson -> lesson.getLessonId() == lessonId)
                .findAny();
        if (toRemove.isPresent()) {
            lessons.remove(toRemove.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Brak lekcji o podanym ID: " + lessonId, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity addLesson(@RequestBody Lesson lesson) {
        if (lessons.stream().anyMatch(l -> l.getLessonId() == lesson.getLessonId())) {
            return new ResponseEntity("Lekcja o podanym id juz istnieje", HttpStatus.NOT_ACCEPTABLE);
        }
        lessons.add(lesson);
        return new ResponseEntity("Dodano lekcje " + lesson, HttpStatus.CREATED);
    }

    @PutMapping("/{lessonId}")
    ResponseEntity editLesson(@PathVariable int lessonId, @RequestBody Lesson updateLesson) {
        if (lessons.stream().noneMatch(l -> l.getLessonId() == lessonId)) {
            return new ResponseEntity("Brak lekcji o podanym id", HttpStatus.NOT_FOUND);
        }
        Lesson lesson = lessons.stream().filter(l -> l.getLessonId() == lessonId).findAny().get();
        lesson.setLessonId(updateLesson.getLessonId());
        lesson.setDate(updateLesson.getDate());
        lesson.setTeacherName(updateLesson.getTeacherName());
        lesson.setStudentName(updateLesson.getStudentName());
        lesson.setTopic(updateLesson.getTopic());
        return new ResponseEntity(lesson.toString(), HttpStatus.OK);

    }
}
