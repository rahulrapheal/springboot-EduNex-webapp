package com.luminar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luminar.entity.CourseVideo;

@Repository
public interface CourseVideoRepository extends JpaRepository<CourseVideo, Long> {

    /**
     * Find all videos for a given course.
     * Returns a list of CourseVideo belonging to the courseId.
     */
    List<CourseVideo> findByCourse_Id(Long courseId);

    /**
     * Find all videos for a given course, ordered by creation time ascending.
     * Useful for displaying videos in the order they were added.
     */
    List<CourseVideo> findByCourse_IdOrderByCreatedAtAsc(Long courseId);

    /**
     * Optional: find videos that are free previews for a course
     */
    List<CourseVideo> findByCourse_IdAndFreePreviewTrue(Long courseId);
}
