package com.luminar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminar.entity.Course;
import com.luminar.repository.CourseRepository;
import com.luminar.repository.EnrollmentRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isEmpty()) return null;

        Course course = optionalCourse.get();
        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        course.setDuration(courseDetails.getDuration());
        course.setFee(courseDetails.getFee());

        return courseRepository.save(course);
    }

    @Override
    public boolean deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) return false;

        long enrollments = enrollmentRepository.countByCourseId(id);
        if (enrollments > 0) {
            throw new RuntimeException(
                    "Cannot delete course. Students are enrolled.");
        }

        courseRepository.deleteById(id);
        return true;
    }
}
