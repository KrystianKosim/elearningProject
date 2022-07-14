package com.kosim.elearning.services;

import com.kosim.elearning.models.dto.TeacherDto;
import com.kosim.elearning.models.entity.TeacherEntity;
import com.kosim.elearning.repsitories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherDto> getAllTeachers() {
        List<TeacherEntity> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(teacherEntity -> TeacherDto.builder()
                        .email(teacherEntity.getEmail())
                        .name(teacherEntity.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<TeacherDto> getSingleTeacher(String email) {
        Optional<TeacherEntity> foundedTeacher = teacherRepository.findTeacherEntityByEmail(email);
            return foundedTeacher.map(teacherEntity -> TeacherDto.builder()
                    .name(teacherEntity.getName())
                    .email(teacherEntity.getEmail())
                    .build());
    }

    public boolean removeTeacher(String email) {
        return teacherRepository.deleteTeacherEntityByEmail(email);
    }

    public boolean addTeacher(TeacherDto teacherDto) {
        Optional<TeacherDto> foundedTeacher = getSingleTeacher(teacherDto.getEmail());
        if (foundedTeacher.isPresent()) {
            return false;
        }
        TeacherEntity teacherEntity = TeacherEntity.builder()
                .name(teacherDto.getName())
                .email(teacherDto.getEmail())
                .build();
         teacherRepository.save(teacherEntity);
         return true;
    }

    public Optional<TeacherDto> editEntireTeacher(String email, TeacherDto updateTeacherDto) {
        Optional<TeacherEntity> foundedTeacher = teacherRepository.findTeacherEntityByEmail(email);
        if (foundedTeacher.isPresent()) {
            TeacherEntity teacherToEdit = foundedTeacher.get();
            teacherToEdit.setEmail(updateTeacherDto.getEmail());
            teacherToEdit.setName(updateTeacherDto.getName());
            teacherRepository.save(teacherToEdit);
        }
        return foundedTeacher.map(teacherEntity -> TeacherDto.builder()
                .name(teacherEntity.getName())
                .email(teacherEntity.getEmail())
                .build());
    }

    public Optional<TeacherDto> editTeacher(String email, TeacherDto updateTeacherDto) {
        Optional<TeacherEntity> foundedTeacher = teacherRepository.findTeacherEntityByEmail(email);
        if (foundedTeacher.isPresent()) {
            TeacherEntity teacherToEdit = foundedTeacher.get();
            Optional.ofNullable(updateTeacherDto.getEmail()).ifPresent(teacherToEdit::setEmail);
            Optional.ofNullable(updateTeacherDto.getName()).ifPresent(teacherToEdit::setName);
            teacherRepository.save(teacherToEdit);
        }

        return foundedTeacher.map(teacherEntity -> TeacherDto.builder()
                .name(teacherEntity.getName())
                .email(teacherEntity.getEmail())
                .build());
    }
}
