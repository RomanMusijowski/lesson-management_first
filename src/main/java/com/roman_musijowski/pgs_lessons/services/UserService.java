package com.roman_musijowski.pgs_lessons.services;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import com.roman_musijowski.pgs_lessons.models.User;

public interface UserService extends CRUDService<User> {

    User saveOrUpdateUserForm(UserForm userForm);
}
