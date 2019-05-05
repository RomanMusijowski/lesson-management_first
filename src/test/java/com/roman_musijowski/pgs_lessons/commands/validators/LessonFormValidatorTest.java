package com.roman_musijowski.pgs_lessons.commands.validators;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
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