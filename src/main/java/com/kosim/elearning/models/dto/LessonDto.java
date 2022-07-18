package com.kosim.elearning.models.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonDto {
    private LocalDateTime date;
    private Long teacherId;
    private List<Long> students;
    private String topic;
}