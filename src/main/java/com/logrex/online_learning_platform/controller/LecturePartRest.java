package com.logrex.online_learning_platform.controller;

import com.logrex.online_learning_platform.entity.LecturePart;
import com.logrex.online_learning_platform.service.LecturePartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/teacher/{teacherId}/courses/{courseId}/lectures/{lectureId}/parts/{partNumber}")
public class LecturePartRest {

    private final LecturePartService partService;

    public LecturePartRest(LecturePartService partService) {
        this.partService = partService;
    }


    @PostMapping
    public ResponseEntity<LecturePart> uploadPart(
            @PathVariable int teacherId,
            @PathVariable int courseId,
            @PathVariable int lectureId,
            @PathVariable int partNumber,
            @RequestParam String title,
            @RequestParam("file") MultipartFile file
    ) {
        LecturePart saved = partService.uploadLecturePart(
                teacherId, courseId, lectureId, partNumber, title, file);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
}
