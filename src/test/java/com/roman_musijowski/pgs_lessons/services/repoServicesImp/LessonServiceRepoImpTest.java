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

    Lesson returnLesson;
    User user;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new LessonServiceRepoImp(userService, lessonRepositoryImp, lessonFormToLesson);

        returnLesson = new Lesson();
        returnLesson.setLesson_id(1L);
        returnLesson.setTitle("Java Spring");

        user = new User();
        user.setId(1L);
        user.setUserName("someUser@gmail.com");
    }

    @Test
    public void listAll() {
        List<Lesson> lessons = new ArrayList<>();

        Lesson lesson = new Lesson();
        lesson.setLesson_id(2L);
        lesson.setTitle("Base of git");

        lessons.add(returnLesson);
        lessons.add(lesson);

        when(lessonRepositoryImp.findAll()).thenReturn(lessons);

        List<Lesson> lessonsNew= service.listAll();

        assertNotNull(lessons);
        assertEquals(2, lessons.size());

        verify(lessonRepositoryImp, times(1));
    }

    @Test
    public void getById() {
        when(lessonRepositoryImp.getOne(anyLong())).thenReturn(returnLesson);
        Lesson lesson = service.getById(1L);

        assertNotNull(lesson);
    }

    @Test
    public void saveOrUpdate() {
        lessonRepositoryImp.save(returnLesson);


    }

//    @Test
//    public void delete() {
//        service.delete(returnLesson);
//
//        verify(lessonRepositoryImp, times(1)).delete(any());
//    }
//
//    @Test
//    void deleteById() {
//        service.delete(returnLesson.getLesson_id());
//
//        verify(lessonRepositoryImp, times(1)).delete(any());
//    }

    @Test
    public void saveOrUpdateLessonForm() {

    }
}