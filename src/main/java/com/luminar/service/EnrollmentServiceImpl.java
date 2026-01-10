package com.luminar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminar.dto.EnrollmentRequestDTO;
import com.luminar.dto.EnrollmentResponseDTO;
import com.luminar.entity.Course;
import com.luminar.entity.Enrollment;
import com.luminar.entity.User;
import com.luminar.repository.CourseRepository;
import com.luminar.repository.EnrollmentRepository;
import com.luminar.repository.UserRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    // ===================== CREATE =====================
    @Override
    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 🔒 Prevent duplicate enrollment
        boolean alreadyEnrolled = enrollmentRepository
                .findByUserId(user.getId())
                .stream()
                .anyMatch(e -> e.getCourse().getId().equals(course.getId()));

        if (alreadyEnrolled) {
            // return a friendly message instead of throwing exception
            EnrollmentResponseDTO dtoResponse = new EnrollmentResponseDTO();
            dtoResponse.setEnrollmentId(null);
            dtoResponse.setStatus("Already Enrolled");
            dtoResponse.setUserId(user.getId());
            dtoResponse.setUserName(user.getName());
            dtoResponse.setCourseId(course.getId());
            dtoResponse.setCourseTitle(course.getName());
            return dtoResponse;
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        // enrollmentDate & status auto-set via @PrePersist

        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToResponseDTO(saved);
    }

    // ===================== READ =====================
    @Override
    public List<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponseDTO getEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return mapToResponseDTO(enrollment);
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByUser(Long userId) {
        return enrollmentRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // ===================== UPDATE =====================
    @Override
    public EnrollmentResponseDTO updateEnrollment(Long enrollmentId, EnrollmentRequestDTO dto) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        if (dto.getStatus() != null) {
            enrollment.setStatus(dto.getStatus());
        }

        Enrollment updated = enrollmentRepository.save(enrollment);
        return mapToResponseDTO(updated);
    }

    // ===================== DELETE =====================
    @Override
    public void deleteEnrollment(Long enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);
    }

    // ===================== MAPPER =====================
    private EnrollmentResponseDTO mapToResponseDTO(Enrollment e) {

        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();

        dto.setEnrollmentId(e.getId());
        dto.setEnrollmentDate(e.getEnrollmentDate());
        dto.setStatus(e.getStatus());

        dto.setUserId(e.getUser().getId());
        dto.setUserName(e.getUser().getName());
        dto.setUserEmail(e.getUser().getEmail());

        dto.setCourseId(e.getCourse().getId());
        dto.setCourseTitle(e.getCourse().getName());
        dto.setCourseFee(e.getCourse().getFee());

        return dto;
    }
}
