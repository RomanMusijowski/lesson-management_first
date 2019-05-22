package com.roman_musijowski.pgs_lessons.converters;

import com.roman_musijowski.pgs_lessons.forms.LessonForm;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class LessonFormToLesson implements Converter<LessonForm, Lesson> {

    @Override
    public Lesson convert(LessonForm lessonForm) {
        Lesson lesson = new Lesson();

        lesson.setLessonId(lessonForm.getLessonId());
        lesson.setTitle(lessonForm.getTitle());
        lesson.setDescription(lessonForm.getDescription());
        lesson.setTeacherInfo(lessonForm.getTeacherInfo());
        lesson.setDate(lessonForm.getDate());

        return lesson;
    }
}
