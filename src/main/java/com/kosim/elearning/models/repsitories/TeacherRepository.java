package com.kosim.elearning.models.repsitories;

import com.kosim.elearning.models.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    Optional<TeacherEntity> findTeacherEntityByEmail(String email);
}
