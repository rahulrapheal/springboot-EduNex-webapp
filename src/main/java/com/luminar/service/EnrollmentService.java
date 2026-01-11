package com.luminar.service;

import java.util.List;

import com.luminar.dto.EnrollmentRequestDTO;
import com.luminar.dto.EnrollmentResponseDTO;

public interface EnrollmentService {

    // ===================== CREATE =====================
    EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto);

    // ===================== READ =====================
    List<EnrollmentResponseDTO> getAllEnrollments();

    EnrollmentResponseDTO getEnrollmentById(Long enrollmentId);

    List<EnrollmentResponseDTO> getEnrollmentsByUser(Long userId);

    List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId);

    // ===================== UPDATE =====================
    EnrollmentResponseDTO updateEnrollment(Long enrollmentId, EnrollmentRequestDTO dto);

    // ===================== DELETE =====================
    void deleteEnrollment(Long enrollmentId);
}
