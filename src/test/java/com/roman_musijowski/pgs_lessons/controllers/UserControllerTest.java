package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.converters.UserToUserForm;
import com.roman_musijowski.pgs_lessons.forms.UserForm;
import com.roman_musijowski.pgs_lessons.forms.validators.UserFormValidator;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
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

public class UserControllerTest {

    @Mock
    UserService userService;
    @Mock
    LessonService lessonService;
    @Mock
    Authentication authentication;
    @Mock
    UserToUserForm userToUserForm;

    @InjectMocks
    UserController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new UserController(new UserFormValidator(), userService,
                userToUserForm, lessonService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }



    @Test
    public void editForAdmin() throws Exception {
        User user = new User();
        UserForm form = new UserForm();
        Long id = 1L;



        //Tell Mockito stub to return new product for ID 1
        when(userService.getById(id)).thenReturn(user);
        when(userToUserForm.convert(any())).thenReturn(form);

        mockMvc.perform(get("/user/editForAdmin/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userForm"))
                .andExpect(model().attribute("userForm", instanceOf(UserForm.class)));
    }

    @Test
    public void listUSers() throws Exception {
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
    public void delete() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));

        verify(userService, times(1)).deleteById(id);
    }

    @Test
    public void saveOrUpdate() throws Exception {
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
                .andExpect(view().name("redirect:/"));

        ArgumentCaptor<UserForm> userCaptor = ArgumentCaptor.forClass(UserForm.class);
        verify(userService).saveOrUpdateUserForm(userCaptor.capture());

        UserForm boundUser = userCaptor.getValue();

        Assert.assertEquals(id, boundUser.getId());
        Assert.assertEquals(userName, boundUser.getUserName());
        Assert.assertEquals(name, boundUser.getName());
        Assert.assertEquals(surName, boundUser.getSurName());
    }

    @Test
    public void newUser() throws Exception {
        Long id = 1L;

        verifyZeroInteractions(userService);

        mockMvc.perform(get("/user/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userForm"))
                .andExpect(model().attribute("userForm", instanceOf(UserForm.class)));
    }
}