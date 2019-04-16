package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/lesson")
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @RequestMapping("/list")
    public String listLessons(Model model){
        model.addAttribute("lessons", lessonService.listAll());
        return "lesson/list";
    }


    @RequestMapping("/show/{id}")
    public String showLesson(@PathVariable Long id, Model model){
        model.addAttribute("lesson", lessonService.getById(id));
        return "lesson/show";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        lessonService.delete(id);
        return "redirect:/lesson/list";
    }

    @RequestMapping("/new")
    public String newLesson(Model model){
        model.addAttribute("lessonForm", new LessonForm());
        return "lesson/lessonForm";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid LessonForm lessonForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "lesson/lessonForm";
        }

        Lesson newLesson= lessonService.saveOrUpdateLessonForm(lessonForm);

        return "redirect:lesson/show/" + newLesson.getLesson_id();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("lessonForm", lessonService.getById(id));
        return "lesson/lessonForm";
    }
}
