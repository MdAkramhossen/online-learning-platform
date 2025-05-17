package com.logrex.online_learning_platform.jpa;

import com.logrex.online_learning_platform.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends CrudRepository<Course, Integer> {
}
