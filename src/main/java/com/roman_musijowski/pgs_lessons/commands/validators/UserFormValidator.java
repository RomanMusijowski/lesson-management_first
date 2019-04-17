package com.roman_musijowski.pgs_lessons.commands.validators;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm userForm= (UserForm) o;

        if(!userForm.getPassword().equals(userForm.getPasswordTextConf())){
            errors.rejectValue("passwordText", "PasswordsDontMatch.customerForm.passwordText", "Passwords Don't Match");
            errors.rejectValue("passwordTextConf", "PasswordsDontMatch.customerForm.passwordTextConf", "Passwords Don't Match");
        }
    }
}