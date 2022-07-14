package com.kosim.elearning.config;

import com.kosim.elearning.models.entity.LessonEntity;
import com.kosim.elearning.models.entity.StudentEntity;
import com.kosim.elearning.models.entity.TeacherEntity;
import com.kosim.elearning.repsitories.LessonRepository;
import com.kosim.elearning.repsitories.StudentRepository;
import com.kosim.elearning.repsitories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Start {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @EventListener(ApplicationReadyEvent.class)
    void runExample(){
        teacherRepository.save(TeacherEntity.builder()
                .name("Anna Kowalska")
                .email("annakowalska@wp.pl")
                .build());
        teacherRepository.save(TeacherEntity.builder()
                .name("Halina Malinowska")
                .email("halinamalinowska@o2.pl")
                .build());
        teacherRepository.save(TeacherEntity.builder()
                .name("Karolina Nowak")
                .email("karolina@gmail.com")
                .build());
        studentRepository.save(StudentEntity.builder()
                        .name("Jan Kowalczyk")
                        .email("jankowalczyk@email")
                        .rate(100)
                        .leadingTeacher(teacherRepository.getById(1L))
                .build());
        studentRepository.save(StudentEntity.builder()
                        .name("Kasia Lewandowska")
                        .email("kasialewandowska@emailexample")
                        .rate(50)
                        .leadingTeacher(teacherRepository.getById(2L))
                .build());

        List<StudentEntity> studentsInLesson = new ArrayList<>();
        studentsInLesson.add(studentRepository.getById(1L));
        lessonRepository.save(LessonEntity.builder()
                        .date(LocalDateTime.now())
                        .teacher(teacherRepository.getById(1L))
                        .topic("Historia")
                        .students(studentsInLesson)
                .build());
    }
}
