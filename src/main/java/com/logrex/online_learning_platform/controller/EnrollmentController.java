package com.logrex.online_learning_platform.controller;
import com.logrex.online_learning_platform.dto.EnrollmentDTO;
import com.logrex.online_learning_platform.dto.RatingDTO;
import com.logrex.online_learning_platform.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;


    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<EnrollmentDTO> enrollInCourse(@PathVariable int studentId, @PathVariable int courseId) {
        EnrollmentDTO dto = enrollmentService.enrollStudent(studentId, courseId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsForStudent(@PathVariable int studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }


    @PostMapping("/student/{studentId}/course/{courseId}/rate")
    public ResponseEntity<EnrollmentDTO> rateCourse(@PathVariable int studentId,
                                                    @PathVariable int courseId,
                                                    @RequestParam int rating,
                                                    @RequestParam(required = false) String comment) {
        EnrollmentDTO dto = enrollmentService.rateCourse(studentId, courseId, rating, comment);
        return ResponseEntity.ok(dto);
    }



}
