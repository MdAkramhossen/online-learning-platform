package com.logrex.online_learning_platform.service;

import com.logrex.online_learning_platform.dto.CourseDTO;
import com.logrex.online_learning_platform.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO dto);

    TeacherDTO getTeacherById(int id);



    List<TeacherDTO> getAllTeachers();

    TeacherDTO updateTeacher(int id, TeacherDTO teacherDTO);

    void deleteTeacher(int id);

    List<CourseDTO> getCoursesByTeacher(int id);
}
