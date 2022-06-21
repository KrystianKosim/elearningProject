package com.kosim.elearning.models;

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
