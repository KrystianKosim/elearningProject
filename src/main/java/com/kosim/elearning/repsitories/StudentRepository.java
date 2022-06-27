package com.kosim.elearning.repsitories;

import com.kosim.elearning.models.dto.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    private static final List<Student> STUDENTS = new ArrayList<>();

    @PostConstruct
    void init() {
        STUDENTS.add(new Student("Mirosław Kowalski", "mirek@gmail.com", "Michał Leja", 200));
        STUDENTS.add(new Student("Jakub Nowicki", "jakub8899wp.pl", "Michał Leja", 200));
        STUDENTS.add(new Student("Jan Nowak", "jannowak@o2.pl", "Adam Kowalczyk", 100));
    }

    public List<Student> findAll() {
        return STUDENTS;
    }

    public Optional<Student> findSingleStudent(String email){
        return STUDENTS.stream()
                .filter(student -> student.getEmail().equals(email))
                .findAny();
    }

    public boolean addStudent(Student student){
        return STUDENTS.add(student);
    }

    public boolean removeStudent(String email){
        return STUDENTS.removeIf(student -> student.getEmail().equals(email));
    }
}
