package com.kosim.elearning.config.modelmapper.mappings;

import com.kosim.elearning.models.dto.LessonDto;
import com.kosim.elearning.models.entity.LessonEntity;
import com.kosim.elearning.models.entity.StudentEntity;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LessonEntityToLessonDTOMapping implements Converter<LessonEntity, LessonDto> {

    @Override
    public LessonDto convert(MappingContext<LessonEntity, LessonDto> mappingContext) {
        LessonEntity source = mappingContext.getSource();
        return LessonDto.builder()
                .date(source.getDate())
                .teacherId(source.getTeacher().getId())
                .students(source.getStudents().stream()
                        .map(StudentEntity::getId)
                        .collect(Collectors.toList()))
                .topic(source.getTopic())
                .build();
    }
}
