package com.kosim.elearning.config.modelmapper.mappings;

import com.kosim.elearning.models.dto.LessonDto;
import com.kosim.elearning.models.entity.LessonEntity;
import com.kosim.elearning.models.repsitories.StudentRepository;
import com.kosim.elearning.models.repsitories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonDTOToLessonEntityMapping implements Converter<LessonDto, LessonEntity> {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public LessonEntity convert(MappingContext<LessonDto, LessonEntity> mappingContext) {
        LessonDto source = mappingContext.getSource();
        return LessonEntity.builder()
                .date(source.getDate())
                .teacher(teacherRepository.getById(source.getTeacherId()))
                .students(source.getStudents().stream()
                        .map(studentRepository::getById)
                        .collect(Collectors.toList()))
                .topic(source.getTopic())
                .build();
    }
}
