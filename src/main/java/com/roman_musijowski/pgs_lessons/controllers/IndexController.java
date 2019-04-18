package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@Controller
public class IndexController {
    private UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/", ""})
    public String index(Model model, Authentication  authentication){

        String email = authentication.getName();
        model.addAttribute("user", userService.findByUserName(email));

        if (email == "admin@gmail.com"){
            return "index";
        }
//        return "index";
        return "user/indexStudent";
    }

    @RequestMapping("/access_denied")
    public String notAuth(){
        return "access_denied";
    }

    @RequestMapping("login")
    public String loginForm(){
        return "login";
    }


    @RequestMapping(value = "/role", method = RequestMethod.GET)
    @ResponseBody
    public String currentRole(Principal principal) {
        String email = principal.getName();

        User user = userService.findByUserName(email);

        return user.getRoles().toString();
    }
}   
