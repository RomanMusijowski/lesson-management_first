package com.roman_musijowski.pgs_lessons.converters;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import com.roman_musijowski.pgs_lessons.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserForm implements Converter<User, UserForm> {
    @Override
    public UserForm convert(User user) {

        UserForm userForm = new UserForm();

        userForm.setId(user.getId());
        userForm.setUserName(user.getUserName());
        userForm.setName(user.getName());
        userForm.setSurName(user.getSurName());
        userForm.setFieldOfStudy(user.getFieldOfStudy());
        userForm.setYearOfStudies(user.getYearOfStudies());
        userForm.setIndex(user.getIndex());

        return userForm;
    }
}
