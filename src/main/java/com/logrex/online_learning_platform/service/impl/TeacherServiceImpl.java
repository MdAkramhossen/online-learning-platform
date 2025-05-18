package com.logrex.online_learning_platform.service.impl;

import com.logrex.online_learning_platform.dto.CourseDTO;
import com.logrex.online_learning_platform.dto.TeacherDTO;
import com.logrex.online_learning_platform.entity.Teacher;
import com.logrex.online_learning_platform.exceptions.ResourceNotFoundException;
import com.logrex.online_learning_platform.jpa.TeacherRepo;
import com.logrex.online_learning_platform.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        return modelMapper.map(this.teacherRepo.save(modelMapper.map(dto, Teacher.class)), TeacherDTO.class);
    }

    @Override
    @Transactional
    public TeacherDTO getTeacherById(int id) {
      Teacher teacher= teacherRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Teacher","id",id));
      return modelMapper.map(teacher, TeacherDTO.class);
    }

    @Override
    @Transactional
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();
        return teachers.stream().map(teacher -> modelMapper.map(teacher,TeacherDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TeacherDTO updateTeacher(int id, TeacherDTO dto) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", id));
        modelMapper.map(dto, teacher);
        return modelMapper.map(teacherRepo.save(teacher), TeacherDTO.class);
    }


    @Override
    public void deleteTeacher(int id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", id));
        teacherRepo.delete(teacher);
    }


    @Override
    public List<CourseDTO> getCoursesByTeacher(int id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", id));
        return teacher.getCourses().stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }



}
