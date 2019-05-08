package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.converters.LessonFormToLesson;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.junit.Before;
import org.junit.Test;
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
    LessonRepositoryImp repo;
    @Mock
    UserService userService;
    @Mock
    LessonFormToLesson lessonFormToLesson;

    Lesson lesson;
    User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new LessonServiceRepoImp(userService, repo, lessonFormToLesson);

        lesson = new Lesson();
        lesson.setLesson_id(5L);
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

        when(repo.findAll()).thenReturn(lessons);

        List<Lesson> lessonsNew = service.listAll();

        assertNotNull(lessons);
        assertEquals(2, lessons.size());

        verify(repo, times(1)).findAll();
    }

    @Test
    public void getById() {
        when(repo.getOne(anyLong())).thenReturn(lesson);
        Lesson lesson = service.getById(5L);

        assertNotNull(lesson);
    }

    @Test
    public void saveOrUpdate() {
        Lesson saveOrUpdate = new Lesson();
        saveOrUpdate.setLesson_id(3L);

        when(repo.save(any())).thenReturn(lesson);

        Lesson savedLesson = service.saveOrUpdate(saveOrUpdate);

        assertNotNull(savedLesson);
        verify(repo).save(any());
    }

    @Test
    public void deleteById() {
        Lesson deleteLesson = new Lesson();
        Long id = 3L;
        deleteLesson.setLesson_id(id);

        when(repo.getOne(any())).thenReturn(deleteLesson);
        Lesson actualLesson = service.deleteById(id);

        assertNotNull(actualLesson);
        verify(repo, times(1)).getOne(any());
        verify(repo, times(1)).deleteById(anyLong());
    }
}