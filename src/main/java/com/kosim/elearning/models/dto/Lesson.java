package com.kosim.elearning.models.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lesson {
    private int lessonId;
    private String date;
    private String teacherName;
    private String studentName;
    private String topic;
}
//todo pozmieniac na lessonDto....