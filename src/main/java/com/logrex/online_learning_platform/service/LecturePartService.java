package com.logrex.online_learning_platform.service;

import com.logrex.online_learning_platform.entity.LecturePart;
import org.springframework.web.multipart.MultipartFile;

public interface LecturePartService {
    LecturePart uploadLecturePart(int teacherId, int courseId, int lectureId, int partNumber, String title, MultipartFile file);
}
