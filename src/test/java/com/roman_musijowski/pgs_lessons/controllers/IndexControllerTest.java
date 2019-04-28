package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    LessonService lessonService;
    @Mock
    UserService userService;

    @InjectMocks
    IndexController controller;

    MockMvc mockMvc;
//    List<User> users;
//    List<Lesson> lessons;

    @BeforeEach
    void setUp() {
//        users = new ArrayList<>();
//        lessons = new ArrayList<>();
//        User user = new User();
//        user.setId(2L);
//        User user2 = new User();
//        user.setId(3L);
//        users.add(user);
//        users.add(user2);
//
//        Lesson lesson= new Lesson();
//        lesson.setLesson_id(4L);
//        Lesson lesson2= new Lesson();
//        lesson.setLesson_id(5L);
//        lessons.add(lesson);
//        lessons.add(lesson2);
//
//
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(controller)
//                .build();
    }

//    @Test
//    void index() throws Exception {
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//
//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("login"));
//    }
//
//    @Test
//    void notAuth() {
//
//    }
//
//    @Test
//    void loginForm() throws Exception {
//        mockMvc.perform(get("/login"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("login"));
//
//        verifyZeroInteractions(userService, lessonService);
//    }
}