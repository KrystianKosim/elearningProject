package com.kosim.elearning.config;

import com.kosim.elearning.models.entity.LessonEntity;
import com.kosim.elearning.models.entity.StudentEntity;
import com.kosim.elearning.models.entity.TeacherEntity;
import com.kosim.elearning.models.repsitories.LessonRepository;
import com.kosim.elearning.models.repsitories.StudentRepository;
import com.kosim.elearning.models.repsitories.TeacherRepository;
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
    void runExample() {
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
        teacherRepository.save(TeacherEntity.builder()
                .name("Mariusz Zalewski")
                .email("zalewski@o2.pl")
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
        studentRepository.save(StudentEntity.builder()
                .name("Arek Testowy")
                .email("ArekTest@mail.com")
                .rate(200)
                .leadingTeacher(teacherRepository.getById(1L))
                .build());
        studentRepository.save(StudentEntity.builder()
                .name("Marek Usuniety")
                .email("marekmail@mail.com")
                .rate(190)
                .leadingTeacher(teacherRepository.getById(1L))
                .build());

        List<StudentEntity> studentsInFirstLesson = new ArrayList<>();
        studentsInFirstLesson.add(studentRepository.getById(1L));
        lessonRepository.save(LessonEntity.builder()
                .date(LocalDateTime.of(2022, 2, 13, 20, 15))
                .teacher(teacherRepository.getById(1L))
                .topic("Historia")
                .students(studentsInFirstLesson)
                .build());

        List<StudentEntity> studentsIntSecondLesson = new ArrayList<>();
        studentsIntSecondLesson.add(studentRepository.getById(1L));
        studentsIntSecondLesson.add(studentRepository.getById(2L));
        lessonRepository.save(LessonEntity.builder()
                .date(LocalDateTime.of(2022, 12, 20, 17, 40))
                .teacher(teacherRepository.getById(2L))
                .topic("Fizyka")
                .students(studentsIntSecondLesson)
                .build());

        List<StudentEntity> studentsInThirdLesson = new ArrayList<>();
        studentsInThirdLesson.add(studentRepository.getById(1L));
        studentsInThirdLesson.add(studentRepository.getById(2L));
        studentsInThirdLesson.add(studentRepository.getById(3L));
        lessonRepository.save(LessonEntity.builder()
                .date(LocalDateTime.of(2022, 10, 10, 10, 10))
                .teacher(teacherRepository.getById(2L))
                .students(studentsInThirdLesson)
                .topic("Angielski")
                .build());
    }
}
