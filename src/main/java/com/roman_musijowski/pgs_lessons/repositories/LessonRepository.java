package com.roman_musijowski.pgs_lessons.repositories;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
