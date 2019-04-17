package com.roman_musijowski.pgs_lessons.commands;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LessonForm {

    private Long lesson_id;

    @NotEmpty
    @Size(min = 5, max = 50)
    private String title;

    @NotEmpty
    @Size(min = 10, max = 50)
    private String teacherInfo;

    @NotEmpty
    @Size(min = 10, max = 200)
    private String description;

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
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
