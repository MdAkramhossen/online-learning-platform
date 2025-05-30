package com.logrex.online_learning_platform.service.impl;
import com.logrex.online_learning_platform.dto.EnrollmentDTO;
import com.logrex.online_learning_platform.dto.RatingDTO;
import com.logrex.online_learning_platform.entity.Course;
import com.logrex.online_learning_platform.entity.Enrollment;
import com.logrex.online_learning_platform.entity.Rating;
import com.logrex.online_learning_platform.entity.Student;
import com.logrex.online_learning_platform.exceptions.ResourceNotFoundException;
import com.logrex.online_learning_platform.jpa.CourseRepo;
import com.logrex.online_learning_platform.jpa.EnrollmentRepo;
import com.logrex.online_learning_platform.jpa.RatingRepo;
import com.logrex.online_learning_platform.jpa.StudentRepo;
import com.logrex.online_learning_platform.service.EnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private  final EnrollmentRepo enrollmentRepo;
    private final ModelMapper modelMapper;
    private  final StudentRepo studentRepo;
    private  final CourseRepo courseRepo;
    private  final RatingRepo ratingRepo;

    public EnrollmentServiceImpl(EnrollmentRepo enrollmentRepo, ModelMapper modelMapper, StudentRepo studentRepo, CourseRepo courseRepo, RatingRepo ratingRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.modelMapper = modelMapper;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.ratingRepo = ratingRepo;
    }


    @Override
    public EnrollmentDTO enrollStudent(int studentId, int courseId) {

        Student student = studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student","studentId",studentId));
        Course course= courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("course","courseId",courseId));
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrolledAt(new Date());
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepo.save(enrollment);
        return modelMapper.map(enrollment, EnrollmentDTO.class);
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByStudent(int studentId) {

        List<Enrollment> getallEnrollment=enrollmentRepo.findByStudentId(studentId);

        return getallEnrollment.stream().map(student-> modelMapper.map(student,EnrollmentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO rateCourse(int studentId, int courseId, int rating, String comment) {

        Student student = studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("student","studentId",studentId));
        Course course= courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("course","courseId",courseId));
        Rating ratings = new Rating();
        ratings.setRatedAt(new Date());
        ratings.setComment(comment);
        ratings.setStudent(student);
        ratings.setCourse(course);
        ratingRepo.save(ratings);
        return modelMapper.map(ratings, EnrollmentDTO.class);
    }


}
