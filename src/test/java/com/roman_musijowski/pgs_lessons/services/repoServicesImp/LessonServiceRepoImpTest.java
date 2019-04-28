package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.converters.LessonFormToLesson;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class LessonServiceRepoImpTest {

    LessonServiceRepoImp service;
    @Mock
    LessonRepositoryImp lessonRepositoryImp;
    @Mock
    UserService userService;
    @Mock
    LessonFormToLesson lessonFormToLesson;

    Lesson lesson;
    User user;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new LessonServiceRepoImp(userService, lessonRepositoryImp, lessonFormToLesson);

        lesson = new Lesson();
        lesson.setLesson_id(1L);
        lesson.setTitle("Java Spring");

        user = new User();
        user.setId(2L);
        user.setUserName("someUser@gmail.com");
    }

    @Test
    public void listAll() {
        List<Lesson> lessons = new ArrayList<>();

        Lesson lesson = new Lesson();
        lesson.setLesson_id(2L);
        lesson.setTitle("Base of git");

        lessons.add(lesson);
        lessons.add(lesson);

        when(lessonRepositoryImp.findAll()).thenReturn(lessons);

        List<Lesson> lessonsNew= service.listAll();

        assertNotNull(lessons);
        assertEquals(2, lessons.size());

        verify(lessonRepositoryImp, times(1)).findAll();
    }

    @Test
    public void getById() {
        when(lessonRepositoryImp.getOne(anyLong())).thenReturn(lesson);
        Lesson lesson = service.getById(1L);

        assertNotNull(lesson);
    }

    @Test
    public void saveOrUpdate() {
        Lesson saveOrUpdate = new Lesson();
        saveOrUpdate.setLesson_id(3L);

        when(lessonRepositoryImp.save(any())).thenReturn(lesson);

        Lesson savedLesson = service.saveOrUpdate(saveOrUpdate);

        assertNotNull(savedLesson);
        verify(lessonRepositoryImp).save(any());
    }

//    @Test
//    void deleteById() {
//
//        service.saveOrUpdate(lesson);
//
//        service.deleteById(1L);
//
//        verify(lessonRepositoryImp).deleteById(any());
//    }
}