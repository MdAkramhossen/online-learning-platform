package com.logrex.online_learning_platform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logrex.online_learning_platform.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureDTO {
    private Long id;
    private String title;
    private Integer orderIndex;
    @JsonIgnore
    private Course course;
    private List<LecturePartDTO> lectureParts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<LecturePartDTO> getLectureParts() {
        return lectureParts;
    }

    public void setLectureParts(List<LecturePartDTO> lectureParts) {
        this.lectureParts = lectureParts;
    }
}