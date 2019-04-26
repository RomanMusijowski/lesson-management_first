package com.roman_musijowski.pgs_lessons.repositories;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepositoryImp extends JpaRepository<Lesson,Long> {
}
