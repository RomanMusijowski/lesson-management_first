package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.converters.UserFormToUser;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.UserRepositoryImp;
import com.roman_musijowski.pgs_lessons.security.services.EncryptionService;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;


class UserServiceRepoImpTest {

    private static final String USER_NAME = "userName@gmail.com";
    UserServiceRepoImp service;
    @Mock
    private UserRepositoryImp userRepositoryImp;
    @Mock
    private UserFormToUser userFormToUser;
    @Mock
    private EncryptionService encryptionService;
    @Mock
    private RoleSevice roleSevice;

    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new UserServiceRepoImp(userRepositoryImp,roleSevice, userFormToUser,encryptionService);

        user = new User();
        user.setId(5L);
        user.setUserName(USER_NAME);
    }

    @Test
    void listAll() {
        List<User> users = new ArrayList<>();
        User user2 = new User();
        user2.setId(2L);

        users.add(user);
        users.add(user2);

        when(userRepositoryImp.findAll()).thenReturn(users);
        List<User> userList = service.listAll();

        assertNotNull(users);
        assertEquals( 2, users.size());

        verify(userRepositoryImp, times(1)).findAll();
    }

    @Test
    void getById() {
        when(userRepositoryImp.getOne(any())).thenReturn(user);
        User user = service.getById(1L);
        assertNotNull(user);
    }

    @Test
    void saveOrUpdate() {
        User saveOrUpdate = new User();
        saveOrUpdate.setId(2L);

        when(userRepositoryImp.save(any())).thenReturn(user);

        User savedUser = service.saveOrUpdate(saveOrUpdate);

        assertNotNull(savedUser);
        verify(userRepositoryImp).save(any());
    }

    @Test
    void delete() {
        User deleteUser = new User();
        Long id = 3L;
        deleteUser.setId(id);

        when(userRepositoryImp.getOne(any())).thenReturn(deleteUser);
        User actualUser = service.deleteById(id);

        verify(userRepositoryImp, times(1)).getOne(any());
        verify(userRepositoryImp, times(1)).deleteById(anyLong());
    }

    @Test
    void findByUserName() {
        when(userRepositoryImp.findByUserName(any())).thenReturn(user);
        User returnedUser = service.findByUserName(USER_NAME);

        assertEquals(USER_NAME, returnedUser.getUserName());
        verify(userRepositoryImp).findByUserName(any());
    }
}