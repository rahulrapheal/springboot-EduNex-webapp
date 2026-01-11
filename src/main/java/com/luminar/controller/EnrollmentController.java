package com.luminar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luminar.dto.EnrollmentRequestDTO;
import com.luminar.dto.EnrollmentResponseDTO;
import com.luminar.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // ===================== CREATE =====================
    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enroll(@RequestBody EnrollmentRequestDTO dto) {
        EnrollmentResponseDTO response = enrollmentService.enrollStudent(dto);
        return ResponseEntity.ok(response);
    }

    // ===================== READ ALL =====================
    @GetMapping
    public List<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // ===================== READ ONE =====================
    @GetMapping("/{id}")
    public EnrollmentResponseDTO getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    // ===================== UPDATE =====================
    @PutMapping("/{id}")
    public EnrollmentResponseDTO updateEnrollment(
            @PathVariable Long id,
            @RequestBody EnrollmentRequestDTO dto) {
        return enrollmentService.updateEnrollment(id, dto);
    }

    // ===================== DELETE =====================
    @DeleteMapping("/{id}")
    public String deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return "Enrollment deleted successfully";
    }
}
