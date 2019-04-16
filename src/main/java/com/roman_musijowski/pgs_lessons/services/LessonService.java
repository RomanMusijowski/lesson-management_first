package com.roman_musijowski.pgs_lessons.services;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import com.roman_musijowski.pgs_lessons.models.Lesson;

public interface LessonService extends CRUDService<Lesson> {
    Lesson saveOrUpdateLessonForm(LessonForm lessonForm);
}
