package com.logrex.online_learning_platform.jpa;

import com.logrex.online_learning_platform.entity.Course;
import com.logrex.online_learning_platform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query("SELECT e.course FROM Enrollment e WHERE e.student.id = :studentId")
    List<Course> findEnrolledCoursesByStudentId(@Param("studentId") int studentId);

}
