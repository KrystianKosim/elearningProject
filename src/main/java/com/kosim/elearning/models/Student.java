package com.kosim.elearning.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
/*
@RequiredArgsContructor
@EqualsAndHashCode
 */
public class Student {
    private String name;
    private String email;
    private String teacher;
    private int rate;
}
