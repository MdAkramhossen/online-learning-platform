package com.logrex.online_learning_platform.service;

import com.logrex.online_learning_platform.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO dto);

    TeacherDTO getTeacherById(int id);

    List<TeacherDTO> getAll();
}
