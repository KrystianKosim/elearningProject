package com.kosim.elearning.services;

import com.kosim.elearning.models.dto.LessonDto;
import com.kosim.elearning.models.entity.LessonEntity;
import com.kosim.elearning.repsitories.LessonRepository;
import com.kosim.elearning.repsitories.StudentRepository;
import com.kosim.elearning.repsitories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public List<LessonDto> getAllLessons() {
        List<LessonEntity> foundedLessons = lessonRepository.findAll();
        return foundedLessons.stream()
                .map(lessonEntity ->  LessonDto.builder()
                        .date(lessonEntity.getDate())
                        .topic(lessonEntity.getTopic())
                        .teacherId(lessonEntity.getTeacher().getId())
                        .students(lessonEntity.getStudents().stream()
                                .map(studentEntity -> studentEntity.getId())
                                .collect(Collectors.toList()))
                        .build()
                )
                .collect(Collectors.toList());
    }

    public Optional<LessonDto> getSingleLesson(Long lessonId) {
        Optional<LessonEntity> foundedLesson = lessonRepository.findById(lessonId);
        return foundedLesson.map(lessonEntity ->  modelMapper.map(lessonEntity,LessonDto.class));
    }

    public boolean removeLessonById(Long lessonId) {
        Optional<LessonEntity> foundedLesson = lessonRepository.findById(lessonId);
        if(foundedLesson.isPresent()){
            lessonRepository.deleteById(lessonId);
            return true;
        }
        return false;
    }

    public boolean addNewLesson(LessonDto lesson) {
        LessonEntity lessonEntity = modelMapper.map(lesson,LessonEntity.class);
        Optional<LessonEntity> foundedLesson = lessonRepository.findById(lessonEntity.getId());
        if (foundedLesson.isPresent()) {
            return false;
        }
        lessonRepository.save(lessonEntity);
        return true;
    }

    public Optional<LessonDto> editEntireLesson(Long lessonId, LessonDto updateLesson) {
        Optional<LessonEntity> foundedLesson = lessonRepository.findById(lessonId);
        if (foundedLesson.isPresent()) {
            LessonEntity lesson = foundedLesson.get();
            lesson.setDate(updateLesson.getDate());
            lesson.setTeacher(teacherRepository.getById(updateLesson.getTeacherId()));
            lesson.setStudents(updateLesson.getStudents().stream()
                    .map(value -> studentRepository.getById(value))
                    .collect(Collectors.toList()));
            lesson.setTopic(updateLesson.getTopic());
        }
        return foundedLesson.map(lessonEntity ->  modelMapper.map(lessonEntity,LessonDto.class));
    }

    public Optional<LessonDto> editLesson(Long lessonId, LessonDto updateLesson) {
        Optional<LessonEntity> foundedLesson = lessonRepository.findById(lessonId);
        if (foundedLesson.isPresent()) {
            LessonEntity lessonEntity = foundedLesson.get();
            Optional.ofNullable(updateLesson.getDate()).ifPresent(lessonEntity::setDate);
            boolean areStudentsIdInList =  Optional.ofNullable(updateLesson.getStudents()).isPresent();
            if(areStudentsIdInList){
                List<Long> studentsId = updateLesson.getStudents();
                lessonEntity.setStudents(studentsId.stream()
                        .map(value -> studentRepository.getById(value))
                        .collect(Collectors.toList()));
            }
            Long teacherId = updateLesson.getTeacherId();
            if(teacherId > 0){
                lessonEntity.setTeacher(teacherRepository.getById(teacherId));
            }
            Optional.ofNullable(updateLesson.getTopic()).ifPresent(lessonEntity::setTopic);
        }
        return foundedLesson.map(lessonEntity ->  modelMapper.map(lessonEntity,LessonDto.class));
    }

}
