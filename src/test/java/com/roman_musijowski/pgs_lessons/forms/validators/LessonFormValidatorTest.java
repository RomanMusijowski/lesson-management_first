package com.roman_musijowski.pgs_lessons.forms.validators;

import com.roman_musijowski.pgs_lessons.forms.LessonForm;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;


class LessonFormValidatorTest {

    private Validator validator;
    private LessonForm lessonForm;
    private Errors errors;

    @Before
    void setUp() {
        validator = new LessonFormValidator();
        lessonForm = new LessonForm();
        errors = new BeanPropertyBindingResult(lessonForm, "lessonForm");
    }

    @Test
    void supports() {
        LocalDateTime localDateTime = LocalDateTime.of(2039,05,15,18,30);
        lessonForm.setDate(localDateTime);
        validator.validate(lessonForm, errors);

        assert errors.hasErrors() == false;
    }

    @Test
    void validate() {
        LocalDateTime localDateTime = LocalDateTime.of(2015,05,15,18,30);
        lessonForm.setDate(localDateTime);
        validator.validate(lessonForm, errors);

        assert errors.hasErrors();
    }
}