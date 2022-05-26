package com.kosim.elearning.repsitories;

import com.kosim.elearning.models.Lesson;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LessonRepository {
    private static final List<Lesson> LESSONS = new ArrayList<>();

    @PostConstruct
    void init() {
        LESSONS.add(new Lesson(1, "22-04-2022", "Malinowska", "Jan Nowak", "Matematyka"));
        LESSONS.add(new Lesson(2, "12-03-2022", "Kowalska", "Adrian Kowalczyk", "Polski"));
        LESSONS.add(new Lesson(3, "01-02-2022", "Wierzbicka", "Adam Lewandowski", "Informatyka"));
        LESSONS.add(new Lesson(4, "05-05-2022", "Szymanski", "Marcin Nowicki", "WF"));
    }

    public List<Lesson> findAll() {
        return LESSONS;
    }

    public Optional<Lesson> findById(int id) {
        return LESSONS.stream()
                .filter(lesson -> lesson.getLessonId() == id)
                .findAny();
    }

    public boolean removeById(int id) {
        return LESSONS.removeIf(lesson -> lesson.getLessonId() == id);
    }

    public boolean addLesson(Lesson lesson){
        return LESSONS.add(lesson);
    }
}
