package com.logrex.online_learning_platform.controller;


import com.logrex.online_learning_platform.dto.CourseDTO;
import com.logrex.online_learning_platform.dto.LectureDTO;
import com.logrex.online_learning_platform.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseRest {

    @Autowired
    private CourseService courseService;


    @PostMapping("/teacher/{teacherId}")
    public ResponseEntity<CourseDTO> createCourse(@PathVariable int teacherId, @RequestBody @Valid CourseDTO courseDTO) {
        CourseDTO created = courseService.createCourse(teacherId, courseDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable int id, @RequestBody @Valid CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }





}
