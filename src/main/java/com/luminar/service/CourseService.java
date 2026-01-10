package com.luminar.service;

import java.util.List;
import com.luminar.entity.Course;

public interface CourseService {

    Course saveCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course updateCourse(Long id, Course course);

    boolean deleteCourseById(Long id);  // must return boolean for controller
}
