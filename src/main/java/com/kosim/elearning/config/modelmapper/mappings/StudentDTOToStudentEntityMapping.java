package com.kosim.elearning.config.modelmapper.mappings;

import com.kosim.elearning.models.dto.StudentDto;
import com.kosim.elearning.models.entity.StudentEntity;
import com.kosim.elearning.models.repsitories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentDTOToStudentEntityMapping implements Converter<StudentDto, StudentEntity> {

    private final TeacherRepository teacherRepository;

    @Override
    public StudentEntity convert(MappingContext<StudentDto, StudentEntity> mappingContext) {
        StudentDto source = mappingContext.getSource();
        return StudentEntity.builder()
                .name(source.getName())
                .email(source.getEmail())
                .leadingTeacher(teacherRepository.getById(source.getTeacherId()))
                .rate(source.getRate())
                .build();
    }
}
