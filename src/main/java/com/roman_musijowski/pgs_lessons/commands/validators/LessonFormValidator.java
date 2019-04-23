package com.roman_musijowski.pgs_lessons.commands.validators;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class LessonFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return LessonForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        LessonForm lessonForm = (LessonForm) o;
        System.out.println("date int the validator - " + lessonForm.getTitle());
        System.out.println("date int the validator - " + lessonForm.getDate());


        if (!lessonForm.getDate().isAfter(LocalDateTime.now())){
            errors.rejectValue("date", "TimeDontMatch.lessonForm.date", "Date Don't Match");
        }
    }
}
