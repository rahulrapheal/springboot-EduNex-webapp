package com.luminar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luminar.entity.CourseVideo;

@Repository
public interface CourseVideoRepository extends JpaRepository<CourseVideo, Long> {

	List<CourseVideo> findByCourse_Id(Long courseId);

	List<CourseVideo> findByCourse_IdOrderByCreatedAtAsc(Long courseId);

	List<CourseVideo> findByCourse_IdAndFreePreviewTrue(Long courseId);
}
