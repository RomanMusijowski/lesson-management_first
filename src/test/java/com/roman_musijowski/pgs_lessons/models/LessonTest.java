package com.roman_musijowski.pgs_lessons.models;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LessonTest {

    Lesson lesson;

    @Before
    public void setUp() throws Exception {
        lesson = new Lesson();

    }

    @Test
    public void getDate() {
        LocalDateTime localDateTime = LocalDateTime.of(2020,05,15,18,30);

        lesson.setDate(localDateTime);
        assertEquals(localDateTime, lesson.getDate());
    }

    @Test
    public void getLesson_id() {
        Long id = 15L;
        lesson.setLesson_id(id);

        assertEquals(id, lesson.getLesson_id());
    }

    @Test
    public void getTitle() {
        String title = "Java";
        lesson.setTitle(title);

        assertEquals(title, lesson.getTitle());
    }

    @Test
    public void getDescription() {
        String description = "First lesson";
        lesson.setDescription(description);

        assertEquals(description, lesson.getDescription());

    }

    @Test
    public void getTeacherInfo() {
        String teacher = "Dr. Urban(New)";
        lesson.setTeacherInfo(teacher);

        assertEquals(teacher, lesson.getTeacherInfo());
    }

    @Test
    public void getUsers() {
        User admin = new User();
        admin.setUserName("admin@gmail.com");
        admin.setName("Roman");
        admin.setSurName("Musiiovskyi");
        admin.setFieldOfStudy("Informatyka");
        admin.setYearOfStudies(2);
        admin.setIndex("89562312457");
        admin.setPassword("admin");

        Set<User> users = new HashSet<>();
        users.add(admin);

        lesson.setUsers(users);
        assertEquals(users,lesson.getUsers());
    }
}