package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

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
        logger.info("Return index page for userName - "+ user.getUserName());

        if (user.getUserName().equals("admin@gmail.com")){
            modelMap.addAttribute("users" ,userService.listAll());
            modelMap.addAttribute("lessons", lessonService.listAll());
            return "user/indexAdmin";
        }


        modelMap.addAttribute("lessons", lessonService.listAll());
        return "lesson/indexStudent";

    }

    @RequestMapping("/access_denied")
    public String notAuth(){
        logger.error("Access denied");
        return "access_denied";
    }

    @RequestMapping("login")
    public String loginForm(){
        logger.info("Login form");
        return "login";
    }
}   
