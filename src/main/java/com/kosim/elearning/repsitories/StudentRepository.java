package com.kosim.elearning.repsitories;

import com.kosim.elearning.models.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findStudentEntityByEmail(String email);

    boolean deleteByEmail(String email);
}
