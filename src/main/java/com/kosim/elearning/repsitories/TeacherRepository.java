package com.kosim.elearning.repsitories;

import com.kosim.elearning.models.dto.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {

    private static final List<Teacher> TEACHERS = new ArrayList<>();

    @PostConstruct
    void init(){
        TEACHERS.add(new Teacher("Halina Malinowska","halinamalinowska@o2.pl"));
        TEACHERS.add(new Teacher("Anna Kowalczyk","kowalczykanna@gmail.com"));
        TEACHERS.add(new Teacher("Jan Nowak","NowakJaan@wp.pl"));
        TEACHERS.add(new Teacher("Marcin Zalewski","zalewski@o2.pl"));
    }

    public List<Teacher> getAllTeachers(){
        return TEACHERS;
    }

    public Optional<Teacher> getSingleTeacher(String email){
        return TEACHERS.stream()
                .filter(teacher -> teacher.getEmail().equals(email))
                .findAny();
    }

    public boolean removeTeacher(String email){
        return TEACHERS.removeIf(teacher -> teacher.getEmail().equals(email));
    }

    public boolean addTeacher(Teacher teacher){
        return TEACHERS.add(teacher);
    }

}
