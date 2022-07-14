package com.kosim.elearning.repsitories;

import com.kosim.elearning.models.entity.TeacherEntity;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    Optional<TeacherEntity> findTeacherEntityByEmail(String email);

    boolean deleteTeacherEntityByEmail(String email);
}
