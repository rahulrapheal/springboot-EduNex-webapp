package com.luminar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luminar.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByUserId(Long userId);

    List<Enrollment> findByCourseId(Long courseId);

    long countByCourseId(Long courseId);
}
