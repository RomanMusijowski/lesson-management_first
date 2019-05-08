package com.roman_musijowski.pgs_lessons.models.security;

import com.roman_musijowski.pgs_lessons.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class RoleTest {

    Role role;

    @Before
    public void setUp() throws Exception {
        role= new Role();
    }

    @Test
    public void getRole_id() {
        Integer role_id = 1;

        role.setRole_id(role_id);
        assertEquals(role_id,role.getRole_id());
    }

    @Test
    public void getRole() {
        String name = "ADMIN";

        role.setRole(name);
        assertEquals(name,role.getRole());
    }

    @Test
    public void getUsers() {
        User user3 = new User();
        user3.setUserName("plabuda@gmail.com");
        user3.setName("Pavlo");
        user3.setSurName("Labuda");
        user3.setFieldOfStudy("Informatyka");
        user3.setYearOfStudies(2);
        user3.setIndex("12235645894");
        user3.setPassword("pavlo");

        Set<User> users = new HashSet<>();
        users.add(user3);

        role.setUsers(users);
        assertEquals(users,role.getUsers());
    }
}