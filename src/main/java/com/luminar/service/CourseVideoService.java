package com.luminar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminar.entity.CourseVideo;
import com.luminar.repository.CourseVideoRepository;

@Service
public class CourseVideoService {

    @Autowired
    private CourseVideoRepository courseVideoRepository;

    // ===================== GET VIDEOS BY COURSE =====================
    public List<CourseVideo> getVideosByCourse(Long courseId) {
        return courseVideoRepository.findByCourse_IdOrderByCreatedAtAsc(courseId);
    }

    // ===================== SAVE =====================
    public CourseVideo saveVideo(CourseVideo video) {
        return courseVideoRepository.save(video);
    }

    // ===================== UPDATE =====================
    public CourseVideo updateVideo(Long videoId, CourseVideo updatedVideo) {

        CourseVideo existingVideo = courseVideoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        existingVideo.setTitle(updatedVideo.getTitle());

        
        existingVideo.setYoutubeUrl(updatedVideo.getYoutubeUrl());

        existingVideo.setFreePreview(updatedVideo.isFreePreview());
        existingVideo.setDuration(updatedVideo.getDuration());

        return courseVideoRepository.save(existingVideo);
    }

    // ===================== DELETE =====================
    public void deleteVideo(Long videoId) {
        CourseVideo video = courseVideoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));
        courseVideoRepository.delete(video);
    }
}
