package com.roman_musijowski.pgs_lessons.forms;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class LessonForm {

    private Long lessonId;

    @NotEmpty
    @Size(min = 5, max = 50)
    private String title;

    @NotEmpty
    @Size(min = 10, max = 50)
    private String teacherInfo;

    @NotEmpty
    @Size(min = 10, max = 200)
    private String description;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(String teacherInfo) {
        this.teacherInfo = teacherInfo;
    }
}
