package com.logrex.online_learning_platform.service.impl;

import com.logrex.online_learning_platform.dto.CourseDTO;
import com.logrex.online_learning_platform.dto.StudentDTO;
import com.logrex.online_learning_platform.entity.Course;
import com.logrex.online_learning_platform.entity.Student;
import com.logrex.online_learning_platform.exceptions.ResourceNotFoundException;
import com.logrex.online_learning_platform.jpa.StudentRepo;
import com.logrex.online_learning_platform.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplement implements StudentService {

    private  final StudentRepo studentRepo;
    private  final ModelMapper modelMapper;

    public StudentServiceImplement(StudentRepo studentRepo, ModelMapper modelMapper) {
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);

        return modelMapper.map(studentRepo.save(student), StudentDTO.class);
    }

    @Override
    @Transactional
    public StudentDTO getStudentById(int id) {
        Student student= studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    @Transactional
    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {
        Student student= studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        studentRepo.save(student);
        return  modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deleteStudent(int id) {

        Student student= studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
        studentRepo.delete(student);
    }

    @Override
    @Transactional
    public List<StudentDTO> getAllStudents() {
        List<Student> students= studentRepo.findAll();
        return students.stream().map(student -> modelMapper.map(student,StudentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getEnrolledCourses(int studentId) {
        List<Course> courses = studentRepo.findEnrolledCoursesByStudentId(studentId);
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }
}
