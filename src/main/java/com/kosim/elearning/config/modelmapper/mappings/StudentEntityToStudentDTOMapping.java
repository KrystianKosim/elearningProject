package com.kosim.elearning.config.modelmapper.mappings;

import com.kosim.elearning.models.dto.StudentDto;
import com.kosim.elearning.models.entity.StudentEntity;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class StudentEntityToStudentDTOMapping implements Converter<StudentEntity, StudentDto> {

    @Override
    public StudentDto convert(MappingContext<StudentEntity, StudentDto> mappingContext) {
        StudentEntity source = mappingContext.getSource();
        return StudentDto.builder()
                .name(source.getName())
                .email(source.getEmail())
                .teacherId(source.getLeadingTeacher().getId())
                .rate(source.getRate())
                .build();
    }
}
