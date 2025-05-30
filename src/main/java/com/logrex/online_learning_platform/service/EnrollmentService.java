package com.logrex.online_learning_platform.service;

import com.logrex.online_learning_platform.dto.EnrollmentDTO;
import com.logrex.online_learning_platform.dto.RatingDTO;


import java.util.List;

public interface EnrollmentService {
    EnrollmentDTO enrollStudent(int studentId, int courseId);
    List<EnrollmentDTO> getEnrollmentsByStudent(int studentId);
    EnrollmentDTO rateCourse(int studentId, int courseId, int rating, String comment);

}
