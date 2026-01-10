package com.luminar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luminar.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
