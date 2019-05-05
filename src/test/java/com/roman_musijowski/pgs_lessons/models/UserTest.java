package com.roman_musijowski.pgs_lessons.models;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void getEnabled() {
        boolean enable = true;

        user.setEnabled(enable);
        assertEquals(enable, user.getEnabled());
    }

    @Test
    public void getUserName() {
        String userName = "rafal@gmail.com";

        user.setUserName(userName);
        assertEquals(userName, user.getUserName());
    }

    @Test
    public void getName() {
        String name = "Kamil";

        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void getSurName() {
        String surName = "Gnus";

        user.setSurName(surName);
        assertEquals(surName, user.getSurName());
    }

    @Test
    public void getYearOfStudies() {
        Integer yearOfStudies = 1;

        user.setYearOfStudies(yearOfStudies);
        assertEquals(yearOfStudies, user.getYearOfStudies());
    }

    @Test
    public void getFieldOfStudy() {
        String fieldOfStudy = "IT";

        user.setFieldOfStudy(fieldOfStudy);
        assertEquals(fieldOfStudy, user.getFieldOfStudy());
    }

    @Test
    public void getIndex() {
        String index = "12564589785";

        user.setIndex(index);
        assertEquals(index, user.getIndex());
    }

    @Test
    public void getPassword() {
        String password = "qwerty";

        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void getId() {
        Long id = 1L;

        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void getEncryptedPassword() {
        String encryptedPassword = "{bcrypt}$2a$10$Q.LS.mnGIHFb.3w5qVUt9O/FiT93F9zvDF9vR5vUo4CQtgzwPffSe";

        user.setEncryptedPassword(encryptedPassword);
        assertEquals(encryptedPassword,user.getEncryptedPassword());
    }

    @Test
    public void getRoles() {
        Role role = new Role();
        role.setRole("Student");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        assertEquals(roles, user.getRoles());
    }

    @Test
    public void getLessons() {
        Lesson lesson = new Lesson();
        LocalDateTime localDateTime = LocalDateTime.of(2019,05,15,18,30);

        lesson.setTitle("Java");
        lesson.setDescription("First lesson");
        lesson.setTeacherInfo("Dr. Koziol");
        lesson.setDate(localDateTime);

        Set<Lesson> lessons = new HashSet<>();
        lessons.add(lesson);

        user.setLessons(lessons);
        assertEquals(lessons, user.getLessons());

    }
}