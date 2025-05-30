package com.logrex.online_learning_platform.controller;

import com.logrex.online_learning_platform.dto.CourseDTO;
import com.logrex.online_learning_platform.dto.TeacherDTO;
import com.logrex.online_learning_platform.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
  @Autowired
  TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        TeacherDTO savedTeacher = teacherService.createTeacher(teacherDTO);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable int id) {
        TeacherDTO dto = teacherService.getTeacherById(id);
            return ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable int id, @RequestBody @Valid TeacherDTO teacherDTO) {
        TeacherDTO updated = teacherService.updateTeacher(id, teacherDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesByTeacher(@PathVariable int id) {
        List<CourseDTO> courses = teacherService.getCoursesByTeacher(id);
        return ResponseEntity.ok(courses);
    }


}
