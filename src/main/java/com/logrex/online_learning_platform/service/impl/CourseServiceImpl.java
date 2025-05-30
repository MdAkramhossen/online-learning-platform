package com.logrex.online_learning_platform.service.impl;
import com.logrex.online_learning_platform.dto.CourseDTO;
import com.logrex.online_learning_platform.dto.LectureDTO;
import com.logrex.online_learning_platform.dto.RatingDTO;
import com.logrex.online_learning_platform.entity.Course;
import com.logrex.online_learning_platform.entity.Lecture;
import com.logrex.online_learning_platform.entity.Rating;
import com.logrex.online_learning_platform.entity.Teacher;
import com.logrex.online_learning_platform.exceptions.ResourceNotFoundException;
import com.logrex.online_learning_platform.jpa.CourseRepo;
import com.logrex.online_learning_platform.jpa.RatingRepo;
import com.logrex.online_learning_platform.jpa.TeacherRepo;
import com.logrex.online_learning_platform.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final TeacherRepo teacherRepo;
    private final CourseRepo courseRepo;
    private final ModelMapper modelMapper;
    private final RatingRepo ratingRepo;

    public CourseServiceImpl(TeacherRepo teacherRepo, CourseRepo courseRepo, ModelMapper modelMapper, RatingRepo ratingRepo) {
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
        this.ratingRepo = ratingRepo;
    }


    @Override
    public CourseDTO createCourse(int teacherId, CourseDTO courseDTO) {

        Teacher teacher = teacherRepo.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException("teacher", "id", teacherId));
        Course course = modelMapper.map(courseDTO, Course.class);
        course.setTeacher(teacher);
        courseRepo.save(course);
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        List<Course> courseList= courseRepo.findAll();
        return courseList.stream().map(course -> modelMapper.map(course,CourseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(int id) {
        Course course= courseRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("course", "id", id));
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO updateCourse(int id, CourseDTO courseDTO) {
        Course course= courseRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("course", "id", id));
       course.setDescription(courseDTO.getDescription());
       course.setTitle(courseDTO.getTitle());

        return modelMapper.map(courseRepo.save(course), CourseDTO.class);
    }

    @Override
    public void deleteCourse(int id) {

        Course course= courseRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("course", "id", id));
        courseRepo.delete(course);

    }


    @Override
    @Transactional
    public List<RatingDTO> getCourseRatings(int courseId) {
        List<Rating> ratings=ratingRepo.findByCourse_Id(courseId);
        System.out.println(ratings.get(0).getComment());
        if (ratings.isEmpty()) {
            return Collections.emptyList();
        }

        return ratings.stream().map(rating -> modelMapper.map(rating, RatingDTO.class)).collect(Collectors.toList());
    }


}
