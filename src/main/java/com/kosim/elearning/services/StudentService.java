package com.kosim.elearning.services;

import com.kosim.elearning.models.dto.StudentDto;
import com.kosim.elearning.models.entity.StudentEntity;
import com.kosim.elearning.models.repsitories.StudentRepository;
import com.kosim.elearning.models.repsitories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public List<StudentDto> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        return students.stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class))
                .collect(Collectors.toList());
    }

    public Optional<StudentDto> getSingleStudent(String email) {
        Optional<StudentEntity> foundedStudent = studentRepository.findStudentEntityByEmail(email);
        return foundedStudent.map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class));
    }

    public boolean addStudent(StudentDto newStudentDto) {
        Optional<StudentEntity> student = studentRepository.findStudentEntityByEmail(newStudentDto.getEmail());
        if (student.isPresent()) {
            return false;
        }
        StudentEntity studentEntityToAdd = modelMapper.map(newStudentDto, StudentEntity.class);
        studentRepository.saveAndFlush(studentEntityToAdd);
        return true;
    }

    public Optional<StudentDto> editEntireStudent(String email, StudentDto updateStudentDto) {
        Optional<StudentEntity> foundedStudent = studentRepository.findStudentEntityByEmail(email);
        if (foundedStudent.isPresent()) {
            StudentEntity studentEntity = foundedStudent.get();
            studentEntity.setName(updateStudentDto.getName());
            studentEntity.setEmail(updateStudentDto.getEmail());
            studentEntity.setLeadingTeacher(teacherRepository.getById(updateStudentDto.getTeacherId()));
            studentEntity.setRate(updateStudentDto.getRate());
            studentRepository.save(studentEntity);
        }
        return foundedStudent.map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class));
    }

    public Optional<StudentDto> editStudent(String email, StudentDto updateStudentDto) {
        Optional<StudentEntity> foundedStudent = studentRepository.findStudentEntityByEmail(email);
        if (foundedStudent.isPresent()) {
            StudentEntity studentToEdit = foundedStudent.get();
            Optional.ofNullable(updateStudentDto.getEmail()).ifPresent(studentToEdit::setEmail);
            Optional.ofNullable(updateStudentDto.getName()).ifPresent(studentToEdit::setName);
            Long teacherId = updateStudentDto.getTeacherId();
            Optional.of(updateStudentDto.getTeacherId()).ifPresent(id -> studentToEdit.setLeadingTeacher(teacherRepository.getById(teacherId)));
            Optional.ofNullable(updateStudentDto.getRate()).ifPresent(studentToEdit::setRate);
            studentRepository.save(studentToEdit);
        }
        return foundedStudent.map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class));
    }

    public boolean removeStudent(String email) {
        Optional<StudentEntity> foundedStudent = studentRepository.findStudentEntityByEmail(email);
        if (foundedStudent.isPresent()) {
            Long studentId = foundedStudent.get().getId();
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }
}
