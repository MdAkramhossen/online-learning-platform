package com.logrex.online_learning_platform.service;

import com.logrex.online_learning_platform.dto.CourseDTO;
import com.logrex.online_learning_platform.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO getStudentById(int id);
    StudentDTO updateStudent(int id, StudentDTO studentDTO);
    void deleteStudent(int id);
    List<StudentDTO> getAllStudents();
    List<CourseDTO> getEnrolledCourses(int studentId);
}
