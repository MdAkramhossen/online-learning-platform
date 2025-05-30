package com.logrex.online_learning_platform.jpa;

import com.logrex.online_learning_platform.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {
    List<Rating> findByCourse_Id(int courseId);
}
