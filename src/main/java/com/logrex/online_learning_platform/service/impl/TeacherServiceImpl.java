package com.logrex.online_learning_platform.service.impl;

import com.logrex.online_learning_platform.dto.TeacherDTO;
import com.logrex.online_learning_platform.entity.Teacher;
import com.logrex.online_learning_platform.exceptions.ResourceNotFoundException;
import com.logrex.online_learning_platform.jpa.TeacherRepo;
import com.logrex.online_learning_platform.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {


    private final TeacherRepo teacherRepo;
    private  final ModelMapper modelMapper;

    public TeacherServiceImpl(TeacherRepo teacherRepo, ModelMapper modelMapper) {
        this.teacherRepo = teacherRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public TeacherDTO createTeacher(TeacherDTO dto) {
        return null;
    }

    @Override
    @Transactional
    public TeacherDTO getTeacherById(int id) {
      Teacher teacher= teacherRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Teacher","id",id));
        System.out.println(teacher.getCourses().get(0).getDescription());
        TeacherDTO dto = modelMapper.map(teacher, TeacherDTO.class);
        return dto;
    }

    @Override
    public List<TeacherDTO> getAll() {
        return List.of();
    }
}
