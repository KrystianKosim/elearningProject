package com.kosim.elearning.models.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String name;
    private String email;
    private String teacher;
    private Integer rate;
}
