package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    private LessonService lessonService;
    private UserService userService;

    @Autowired
    public IndexController(LessonService lessonService, UserService userService) {
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @RequestMapping({"/", ""})
    public String index(ModelMap modelMap, Authentication  authentication){

        User user = userService.findByUserName(authentication.getName());
        modelMap.addAttribute("user", user);


        for (Object lesson : lessonService.listAll()){
            System.out.println("Lessons - ");
            System.out.println(lesson.toString());
        }

        if (user.getUserName() == "admin@gmail.com"){
            modelMap.addAttribute("users" ,userService.listAll());
            modelMap.addAttribute("lessons", lessonService.listAll());
            return "user/indexAdmin";
        }

        System.out.println("Login userName - " + authentication.getName());
        modelMap.addAttribute("lessons", lessonService.listAll());
        return "lesson/listForStudent";

    }

    @RequestMapping("/access_denied")
    public String notAuth(){
        return "access_denied";
    }

    @RequestMapping("login")
    public String loginForm(){
        return "login";
    }


//    @RequestMapping(value = "/role", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentRole(Principal principal) {
//        String email = principal.getName();
//
//        User user = userService.findByUserName(email);
//
//        return user.getRoles().toString();
//    }
}   
