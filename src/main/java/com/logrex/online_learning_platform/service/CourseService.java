package com.logrex.online_learning_platform.service;

import com.logrex.online_learning_platform.dto.CourseDTO;
import com.logrex.online_learning_platform.dto.LectureDTO;
import com.logrex.online_learning_platform.dto.RatingDTO;

import java.util.List;

public interface CourseService {
    CourseDTO createCourse(int teacherId, CourseDTO courseDTO);

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(int id);

    CourseDTO updateCourse(int id, CourseDTO courseDTO);

    void deleteCourse(int id);


    List<RatingDTO> getCourseRatings(int courseId);
}
