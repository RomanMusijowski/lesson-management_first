package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.converters.LessoneToLessonForm;
import com.roman_musijowski.pgs_lessons.forms.LessonForm;
import com.roman_musijowski.pgs_lessons.forms.validators.LessonFormValidator;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/lesson")
public class LessonController {

    private static final Logger logger = LoggerFactory.getLogger(LessonController.class);


    private LessonService lessonService;
    private LessonFormValidator lessonFormValidator;
    private LessoneToLessonForm lessoneToLessonForm;


    @Autowired
    public LessonController(LessonFormValidator lessonFormValidator, LessonService lessonService,
                            LessoneToLessonForm lessoneToLessonForm) {
        this.lessonFormValidator = lessonFormValidator;
        this.lessonService = lessonService;
        this.lessoneToLessonForm = lessoneToLessonForm;
    }

    @RequestMapping("/list")
    public String listLessons(Model model){
        logger.info("Get list of lessons");

        model.addAttribute("lessons", lessonService.listAll());
        return "lesson/list";
    }


    @RequestMapping("/show/{id}")
    public String showLesson(@PathVariable Long id, Model model){
        logger.info("Show by id - " + id);

        model.addAttribute("lesson", lessonService.getById(id));
        return "lesson/show";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        logger.info("Delete by id - " + id);

        Lesson lesson = lessonService.getById(id);


        if (!lesson.getDate().isAfter(LocalDateTime.now())){
            logger.info("You can't deleteById this lesson it's too late");
            return "redirect:/lesson/list";
        }else {
            lessonService.deleteById(id);
            return "redirect:/lesson/list";
        }
    }

    @RequestMapping("/new")
    public String newLesson(Model model){
        logger.info("New lesson");

        model.addAttribute("lessonForm", new LessonForm());
        return "lesson/lessonForm";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid LessonForm lessonForm, BindingResult bindingResult){
        logger.info("Save or update");

        lessonFormValidator.validate(lessonForm, bindingResult);

        if (bindingResult.hasErrors()){
            return "lesson/lessonForm";
        }

        Lesson newLesson= lessonService.saveOrUpdateLessonForm(lessonForm);

        return "redirect:lesson/show/" + newLesson.getLessonId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        logger.info("Edit lesson by id - "+ id);

        Lesson lesson = lessonService.getById(id);
        LessonForm lessonForm = lessoneToLessonForm.convert(lesson);

        model.addAttribute("lessonForm", lessonForm);
        return "lesson/lessonForm";
    }
}
