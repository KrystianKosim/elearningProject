package com.kosim.elearning.models.repsitories;

import com.kosim.elearning.models.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
    void deleteById(Long id);

}
