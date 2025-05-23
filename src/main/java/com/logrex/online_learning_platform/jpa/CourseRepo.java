package com.logrex.online_learning_platform.jpa;

import com.logrex.online_learning_platform.entity.Course;
import com.logrex.online_learning_platform.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {


}
