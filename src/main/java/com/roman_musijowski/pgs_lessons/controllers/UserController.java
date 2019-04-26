package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import com.roman_musijowski.pgs_lessons.commands.validators.UserFormValidator;
import com.roman_musijowski.pgs_lessons.converters.UserToUserForm;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    private LessonService lessonService;
    private UserFormValidator userFormValidator;
    private UserToUserForm userToUserForm;
    private UserService userService;

    @Autowired
    public UserController(UserFormValidator userFormValidator, UserService userService,
                          UserToUserForm userToUserForm, LessonService lessonService) {
        this.lessonService = lessonService;
        this.userFormValidator = userFormValidator;
        this.userToUserForm = userToUserForm;
        this.userService = userService;
    }



    @RequestMapping("/edit")
    public String edit(Model model, Authentication authentication) {

        User user = userService.findByUserName(authentication.getName());

        model.addAttribute("userForm", userToUserForm.convert(user));
        return "user/userForm";
    }


    @RequestMapping("/list")
    public String listUSers(Model model){
        model.addAttribute("users", userService.listAll());
        return "user/list";
    }



    @RequestMapping(value = "addLesson/{id}")
    public String addLesson(@PathVariable Long id, Authentication authentication){
        User user = userService.findByUserName(authentication.getName());
        Lesson lesson = lessonService.getById(id);
        user.addLesson(lesson);

        userService.saveOrUpdate(user);
        System.out.println("Added lesson");


        return "redirect:/";
    }

    @RequestMapping(value = "removeLesson/{id}")
    public String removeLesson(@PathVariable Long id, Authentication authentication){
        User user = userService.findByUserName(authentication.getName());
        Lesson lesson = lessonService.getById(id);
        user.removeLesson(lesson);

        userService.saveOrUpdate(user);
        System.out.println("Removed lesson");


        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        userService.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid UserForm userForm, BindingResult bindingResult){

        System.out.println("User id - "+userForm.getId());

        userFormValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()){
            return "user/userForm";
        }

        User newUser = userService.saveOrUpdateUserForm(userForm);

        return "redirect:/";
    }

    @RequestMapping("/editForAdmin/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("userForm", userToUserForm.convert(userService.getById(id)));
        return "user/userForm";
    }

    @RequestMapping("/show/{id}")
    public String showUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }

    @RequestMapping("/new")
    public String newUser(Model model){
        model.addAttribute("userForm", new UserForm());
        return "user/userForm";
    }

}

