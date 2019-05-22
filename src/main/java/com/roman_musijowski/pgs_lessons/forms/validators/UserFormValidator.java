package com.roman_musijowski.pgs_lessons.forms.validators;

import com.roman_musijowski.pgs_lessons.forms.UserForm;
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

        if(!userForm.getPassword().equals(userForm.getEncryptedPassword())){
            errors.rejectValue("password", "PasswordsDontMatch.customerForm.password", "Passwords Don't Match");
            errors.rejectValue("encryptedPassword", "PasswordsDontMatch.customerForm.encryptedPassword", "Passwords Don't Match");
        }
    }
}
