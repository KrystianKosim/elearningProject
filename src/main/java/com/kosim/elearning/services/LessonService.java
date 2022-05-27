package com.kosim.elearning.services;

import com.kosim.elearning.models.Lesson;
import com.kosim.elearning.repsitories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getSingleLesson(int lessonId) {
        return lessonRepository.findById(lessonId);
    }

    public boolean removeLessonById(int lessonId) {
        return lessonRepository.removeById(lessonId);
    }

    public boolean addNewLesson(Lesson lesson) {
        Optional<Lesson> foundedLesson = lessonRepository.findById(lesson.getLessonId());
        if (foundedLesson.isPresent()) {
            return false;
        }
        return lessonRepository.addLesson(lesson);
    }

    public Optional<Lesson> editEntireLesson(int lessonId, Lesson updateLesson) {
        Optional<Lesson> foundedLesson = lessonRepository.findById(lessonId);
        if (foundedLesson.isPresent()) {
            Lesson lesson = foundedLesson.get();
            lesson.setLessonId(updateLesson.getLessonId());
            lesson.setDate(updateLesson.getDate());
            lesson.setTeacherName(updateLesson.getTeacherName());
            lesson.setStudentName(updateLesson.getStudentName());
            lesson.setTopic(updateLesson.getTopic());
        }
        return foundedLesson;
    }

    public Optional<Lesson> editLesson(int lessonId, Lesson lesson){
        Optional<Lesson> foundedLesson = lessonRepository.findById(lessonId);
        if(foundedLesson.isPresent()){
            Lesson newLesson = foundedLesson.get();
            Optional.ofNullable(lesson.getDate()).ifPresent(newLesson::setDate);
            Optional.ofNullable(lesson.getTeacherName()).ifPresent(newLesson::setTeacherName);
            Optional.ofNullable(lesson.getStudentName()).ifPresent(newLesson::setStudentName);
            Optional.ofNullable(lesson.getTopic()).ifPresent(newLesson::setTopic);
        }
        return foundedLesson;
    }

}
