package com.roman_musijowski.pgs_lessons.converters;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.models.security.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserToUserDetailsTest {

    private Converter<User, UserDetails> converter;

    @Before
    public void setUp() throws Exception {
        converter = new UserToUserDetails();
    }

    @Test
    public void convert() {
        String userName = "admin@gmail.com";
        String password = "password";
        String roleName1 = "STUDENT";
        String roleName2 = "ADMIN";

        Role role1 = new Role();
        role1.setRole_id(1);
        role1.setRole(roleName1);

        Role role2 = new Role();
        role2.setRole_id(2);
        role2.setRole(roleName2);

        User user = new User();
        user.setUserName(userName);
        user.setEncryptedPassword(password);
        user.addRole(role1);
        user.addRole(role2);

        UserDetails userDetails = converter.convert(user);

        assertEquals(userDetails.getUsername(), userName);
        assertEquals(userDetails.getPassword(), password);
        for (Object o : userDetails.getAuthorities()) {
            System.out.println(o.toString());
        }

        assertEquals(2, userDetails.getAuthorities().size());
    }
}