package com.kosim.elearning.services;

import com.kosim.elearning.models.Teacher;
import com.kosim.elearning.repsitories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAllTeachers();
    }

    public Optional<Teacher> getSingleTeacher(String email) {
        return teacherRepository.getSingleTeacher(email);
    }

    public boolean removeTeacher(String email) {
        return teacherRepository.removeTeacher(email);
    }

    public boolean addTeacher(Teacher teacher) {
        Optional<Teacher> foundedTeacher = getSingleTeacher(teacher.getEmail());
        if (foundedTeacher.isPresent()) {
            return false;
        }
        return teacherRepository.addTeacher(teacher);
    }

    public Optional<Teacher> editEntireTeacher(String email, Teacher updateTeacher) {
        Optional<Teacher> foundedTeacher = teacherRepository.getSingleTeacher(email);
        if (foundedTeacher.isPresent()) {
            Teacher teacher = foundedTeacher.get();
            teacher.setEmail(updateTeacher.getEmail());
            teacher.setNameAndSurname(updateTeacher.getNameAndSurname());
        }
        return foundedTeacher;
    }

    public Optional<Teacher> editTeacher(String email, Teacher updateTeacher) {
        Optional<Teacher> foundedTeacher = teacherRepository.getSingleTeacher(email);
        if (foundedTeacher.isPresent()) {
            Teacher teacher = foundedTeacher.get();
            Optional.ofNullable(updateTeacher.getEmail()).ifPresent(teacher::setEmail);
            Optional.ofNullable(updateTeacher.getNameAndSurname()).ifPresent(teacher::setNameAndSurname);
        }
        return foundedTeacher;
    }
}
