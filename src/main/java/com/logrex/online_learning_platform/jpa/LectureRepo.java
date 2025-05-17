package com.logrex.online_learning_platform.jpa;

import com.logrex.online_learning_platform.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepo extends JpaRepository<Lecture, Integer> {
}
