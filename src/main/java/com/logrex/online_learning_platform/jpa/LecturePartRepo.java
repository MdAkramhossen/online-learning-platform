package com.logrex.online_learning_platform.jpa;

import com.logrex.online_learning_platform.entity.LecturePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturePartRepo extends JpaRepository<LecturePart, Integer> {
}
