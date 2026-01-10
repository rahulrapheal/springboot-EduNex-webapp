
package com.luminar.dto;

import java.time.LocalDate;

public class EnrollmentResponseDTO {
    private Long enrollmentId;
    private LocalDate enrollmentDate;
    private String status;

    private Long userId;
    private String userName;
    private String userEmail;

    private Long courseId;
    private String courseTitle;
    private double courseFee;

    // Getters and Setters
    public Long getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(Long enrollmentId) { this.enrollmentId = enrollmentId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId =  courseId; }
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public double getCourseFee() { return courseFee; }
    public void setCourseFee(double courseFee) { this.courseFee = courseFee; }
}
