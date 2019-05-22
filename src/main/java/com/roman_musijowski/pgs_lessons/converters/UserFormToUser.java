package com.roman_musijowski.pgs_lessons.converters;

import com.roman_musijowski.pgs_lessons.forms.UserForm;
import com.roman_musijowski.pgs_lessons.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class UserFormToUser implements Converter<UserForm, User> {

    @Override
    public User convert(UserForm userForm) {
        User user = new User();

        user.setId(userForm.getId());
        user.setUserName(userForm.getUserName());
        user.setName(userForm.getName());
        user.setSurName(userForm.getSurName());
        user.setFieldOfStudy(userForm.getFieldOfStudy());
        user.setYearOfStudies(userForm.getYearOfStudies());
        user.setPassword(userForm.getPassword());
        user.setIndex(userForm.getIndex());

        return user;
    }
}
