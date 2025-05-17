package com.logrex.online_learning_platform.controller;

import com.logrex.online_learning_platform.dto.TeacherDTO;
import com.logrex.online_learning_platform.service.TeacherService;
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

//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<TeacherDTO>> getTeacher(@PathVariable int id) {
//        TeacherDTO dto = teacherService.getTeacherById(id);
//        return ResponseEntity.ok(new ApiResponse<>(true, "Teacher fetched", dto));
//    }
        @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable int id) {
        TeacherDTO dto = teacherService.getTeacherById(id);
            return ResponseEntity.ok(dto);
    }


}
