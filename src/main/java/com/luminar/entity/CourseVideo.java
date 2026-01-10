package com.luminar.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;

@Entity
@Table(name = "course_videos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    
    @Column(name="description")
    private String description;
    

    // ✅ SINGLE SOURCE OF TRUTH
    @Column(nullable = false, length = 1000)
    @JsonProperty("youtubeUrl")              // JSON name = url
    private String youtubeUrl;         // Java name

    @Column(nullable = false)
    private boolean freePreview = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    private Integer duration;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ================= GETTERS / SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // ✅ ONLY THIS GETTER
    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    // ✅ ONLY THIS SETTER
    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public boolean isFreePreview() {
        return freePreview;
    }

    public void setFreePreview(boolean freePreview) {
        this.freePreview = freePreview;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
