package com.klb.controller;

import com.klb.config.SecurityConfig;
import com.klb.entity.User;
import com.klb.service.EmailService;
import com.klb.service.UserService;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;

/**
 * Created by fmkam on 09.07.2017.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(){
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
   
    public String register(@ModelAttribute User model){
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(SecurityConfig.PASSWORD_STRENGHT);
            String encodedPassword = encoder.encode(model.getPassword());
            model.setPassword(encodedPassword); 
            userService.save(model);
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            return "redirect:/register";
        }
        
        emailService.sendEmail("biblioteka@onet.pl", model.getEmail(),"Welcome to out library", "Registration completed");
        return "redirect:/login";
    }
}
