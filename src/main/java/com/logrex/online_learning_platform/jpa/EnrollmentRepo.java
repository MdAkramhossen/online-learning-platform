package com.logrex.online_learning_platform.jpa;

import com.logrex.online_learning_platform.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(int studentId);

}
