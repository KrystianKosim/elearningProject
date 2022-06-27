package com.kosim.elearning.services;

import com.kosim.elearning.models.dto.Student;
import com.kosim.elearning.repsitories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getSingleStudent(String email) {
        return studentRepository.findSingleStudent(email);
    }

    public boolean addStudent(Student newStudent) {
        Optional<Student> student = studentRepository.findSingleStudent(newStudent.getEmail());
        if (student.isPresent()) {
            return false;
        }
        return studentRepository.addStudent(newStudent);
    }

    public Optional<Student> editEntireStudent(String email, Student updateStudent) {
        Optional<Student> foundedStudent = studentRepository.findSingleStudent(email);
        if (foundedStudent.isPresent()) {
            Student student = foundedStudent.get();
            student.setEmail(updateStudent.getEmail());
            student.setName(updateStudent.getName());
            student.setTeacher(updateStudent.getTeacher());
            student.setRate(updateStudent.getRate());
        }
        return foundedStudent;
    }

    public Optional<Student> editStudent(String email, Student updateStudent) {
        Optional<Student> foundedStudent = studentRepository.findSingleStudent(email);
        if (foundedStudent.isPresent()) {
            Student student = foundedStudent.get();
            /**
             * Dlaczego musze to napisać tak student::setEmail a nie tak : student.setEmail(updateStudent.getEmail())
             */
            Optional.ofNullable(updateStudent.getEmail()).ifPresent(student::setEmail);
            Optional.ofNullable(updateStudent.getName()).ifPresent(student::setName);
            Optional.ofNullable(updateStudent.getTeacher()).ifPresent(student::setTeacher);
            if (updateStudent.getRate() > 0) {
                student.setEmail(updateStudent.getEmail());
            }
            return foundedStudent;
        }
        return foundedStudent;
    }

    public boolean removeStudent(String email) {
        return studentRepository.removeStudent(email);
    }
}
