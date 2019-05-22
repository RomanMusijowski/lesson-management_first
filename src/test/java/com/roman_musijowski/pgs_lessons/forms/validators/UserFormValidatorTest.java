package com.roman_musijowski.pgs_lessons.forms.validators;

import com.roman_musijowski.pgs_lessons.forms.UserForm;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


class UserFormValidatorTest {

    UserFormValidator validator;
    UserForm userForm;
    Errors errors;

    @Before
    void setUp() {
        validator = new UserFormValidator();
        userForm = new UserForm();
        errors =new BeanPropertyBindingResult(userForm,"userForm");
    }

    @Test
    void testNoErrors() {
        userForm.setPassword("password");
        userForm.setEncryptedPassword("password");

        validator.validate(userForm,errors);

        assert errors.hasErrors() == false;
    }

    @Test
    void testHasErrors() {
        userForm.setPassword("password");
        userForm.setEncryptedPassword("qwerty");

        validator.validate(userForm,errors);

        assert errors.hasErrors();
    }
}