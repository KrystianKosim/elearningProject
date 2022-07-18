package com.kosim.elearning.models.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private String name;
    private String email;
    private long teacherId;
    private Integer rate;
}
