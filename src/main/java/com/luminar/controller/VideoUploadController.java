package com.luminar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.luminar.entity.Course;
import com.luminar.entity.CourseVideo;
import com.luminar.repository.CourseRepository;
import com.luminar.service.CourseVideoService;

@RestController
@RequestMapping("/course_videos")
@CrossOrigin(origins = "*")
public class VideoUploadController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseVideoService courseVideoService;

    // ================= ADD VIDEO =================
    @PostMapping("/save/{courseId}")
    public ResponseEntity<CourseVideo> saveVideo(
            @PathVariable Long courseId,
            @RequestBody CourseVideo video) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Course not found with id " + courseId));

        video.setId(null); // IMPORTANT: avoid accidental update
        video.setCourse(course);

        CourseVideo savedVideo = courseVideoService.saveVideo(video);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVideo);
    }

    // ================= GET VIDEOS BY COURSE =================
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseVideo>> getVideosByCourse(
            @PathVariable Long courseId) {

        if (!courseRepository.existsById(courseId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course not found with id " + courseId);
        }

        List<CourseVideo> videos =
                courseVideoService.getVideosByCourse(courseId);

        return ResponseEntity.ok(videos);
    }

    // ================= UPDATE VIDEO =================
    @PutMapping("/update/{videoId}")
    public ResponseEntity<CourseVideo> updateVideo(
            @PathVariable Long videoId,
            @RequestBody CourseVideo updatedVideo) {

        CourseVideo video =
                courseVideoService.updateVideo(videoId, updatedVideo);

        return ResponseEntity.ok(video);
    }

    // ================= DELETE VIDEO =================
    @DeleteMapping("/{videoId}")
    public ResponseEntity<Void> deleteVideo(
            @PathVariable Long videoId) {

        courseVideoService.deleteVideo(videoId);
        return ResponseEntity.noContent().build();
    }
}
