package com.roman_musijowski.pgs_lessons.converters;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LessoneToLessonForm implements Converter<Lesson, LessonForm> {
    @Override
    public LessonForm convert(Lesson lesson) {

        LessonForm lessonForm = new LessonForm();

        lessonForm.setLesson_id(lesson.getLesson_id());
        lessonForm.setTitle(lesson.getTitle());
        lessonForm.setDescription(lesson.getDescription());
        lessonForm.setTeacherInfo(lesson.getTeacherInfo());
        lessonForm.setDate(lesson.getDate());

        return lessonForm;
    }
}
