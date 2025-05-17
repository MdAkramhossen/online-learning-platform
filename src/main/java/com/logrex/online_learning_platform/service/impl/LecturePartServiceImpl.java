package com.logrex.online_learning_platform.service.impl;

import com.logrex.online_learning_platform.entity.Course;
import com.logrex.online_learning_platform.entity.Lecture;
import com.logrex.online_learning_platform.entity.LecturePart;
import com.logrex.online_learning_platform.entity.Teacher;
import com.logrex.online_learning_platform.jpa.CourseRepo;
import com.logrex.online_learning_platform.jpa.LecturePartRepo;
import com.logrex.online_learning_platform.jpa.LectureRepo;
import com.logrex.online_learning_platform.jpa.TeacherRepo;
import com.logrex.online_learning_platform.service.AzureBlobStorageService;
import com.logrex.online_learning_platform.service.LecturePartService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class LecturePartServiceImpl implements LecturePartService {

    private final TeacherRepo teacherRepo;
    private final CourseRepo courseRepo;
    private final LectureRepo lectureRepo;
    private final LecturePartRepo partRepo;
    private final AzureBlobStorageService storageService;

    public LecturePartServiceImpl(TeacherRepo teacherRepo, CourseRepo courseRepo, LectureRepo lectureRepo, LecturePartRepo partRepo, AzureBlobStorageService storageService) {
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
        this.lectureRepo = lectureRepo;
        this.partRepo = partRepo;
        this.storageService = storageService;
    }

    @Override
    public LecturePart uploadLecturePart(int teacherId, int courseId, int lectureId, int partNumber, String title, MultipartFile file) {

        Teacher teacher = teacherRepo.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + teacherId));
///////// global exception will be implement

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));


        Lecture lecture = lectureRepo.findById(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("Lecture not found with ID: " + lectureId));

        // 4. Generate Safe & Unique Blob Path
        String uuid = UUID.randomUUID().toString();
        String orig = StringUtils.cleanPath(file.getOriginalFilename());

        String blobPath = String.format("courses/%d/lectures/%d/part_%d_%s-%s",
                courseId, lectureId, partNumber, uuid, orig);


        // 5. Upload to Azure Blob Storage
        String fileUrl = storageService.uploadFile(blobPath, file); // Will return public URL

        // 6. Save Lecture Part to DB
        LecturePart part = new LecturePart();
//        part.setLecture(lecture);
//        part.setPartNumber(partNumber);
        part.setTitle(title);
        part.setFileType(file.getContentType());
        part.setFileUrl(fileUrl);

        return partRepo.save(part);
    }



}
