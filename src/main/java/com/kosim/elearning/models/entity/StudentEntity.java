package com.kosim.elearning.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "Student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    // Główny nauczyciel
    @ManyToOne
    private TeacherEntity leadingTeacher;

    private Integer rate;

//    @OneToMany(mappedBy = "students")
//    private List<LessonEntity> lessons;
}
