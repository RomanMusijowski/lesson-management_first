package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import com.roman_musijowski.pgs_lessons.commands.validators.UserFormValidator;
import com.roman_musijowski.pgs_lessons.converters.UserToUserForm;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    UserService userService;
    @Mock
    LessonService lessonService;

    @InjectMocks
    UserController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new UserController(new UserFormValidator(), userService,
                 new UserToUserForm(),lessonService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void edit() {

    }

    @Test
    void listUSers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        //specific Mockito interaction, tell stub to return list of products
        when(userService.listAll()).thenReturn((List)users); //need to strip generics to keep Mockito happy.

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attribute("users",hasSize(2)));
    }

    @Test
    void addLesson() {
    }

    @Test
    void removeLesson() {
    }

    @Test
    void delete() {
    }

    @Test
    void saveOrUpdate() throws Exception {
        Long id = 1L;
        String userName = "test@gmail.com";
        String name = "Test description";
        String surName = "Test teacherInfo";
        Integer yearOfStudies = 1;
        String fieldOfStudy = "Test teacherInfo";
        String index = "45897856122";
        String password = "qwerty";

        User returnUser = new User();
        returnUser.setId(id);
        returnUser.setUserName(userName);
        returnUser.setName(name);
        returnUser.setSurName(surName);
        returnUser.setYearOfStudies(yearOfStudies);
        returnUser.setFieldOfStudy(fieldOfStudy);
        returnUser.setIndex(index);
        returnUser.setPassword(password);



        when(userService.saveOrUpdateUserForm(any(UserForm.class))).thenReturn(returnUser);
        when(userService.getById(anyLong())).thenReturn(returnUser);

        mockMvc.perform(post("/user")
                .param("id", String.valueOf(id))
                .param("userName", userName)
                .param("name", name)
                .param("surName", surName)
                .param("yearOfStudies", "1")
                .param("fieldOfStudy", fieldOfStudy)
                .param("index", index)
                .param("password", password)
                .param("encryptedPassword", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:user/show/1"));

        ArgumentCaptor<UserForm> userCaptor = ArgumentCaptor.forClass(UserForm.class);
        verify(userService).saveOrUpdateUserForm(userCaptor.capture());

        UserForm boundUser = userCaptor.getValue();

        Assert.assertEquals(id, boundUser.getId());
        Assert.assertEquals(userName, boundUser.getUserName());
        Assert.assertEquals(name, boundUser.getName());
        Assert.assertEquals(surName, boundUser.getSurName());
    }

    @Test
    void edit1() throws Exception {
        Long id = 1L;

        //Tell Mockito stub to return new product for ID 1
        when(userService.getById(id)).thenReturn(new User());

        mockMvc.perform(get("/user/editForAdmin/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userForm"))
                .andExpect(model().attribute("userForm", instanceOf(UserForm.class)));
    }

    @Test
    void showUser() throws Exception {
        Long id = 1L;

        //Tell Mockito stub to return new product for ID 1
        when(userService.getById(id)).thenReturn(new User());

        mockMvc.perform(get("/user/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/show"))
                .andExpect(model().attribute("user", instanceOf(User.class)));
    }

    @Test
    void newUser() throws Exception {
        Long id = 1L;

        verifyZeroInteractions(userService);

        mockMvc.perform(get("/user/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userForm"))
                .andExpect(model().attribute("userForm", instanceOf(UserForm.class)));

    }
}