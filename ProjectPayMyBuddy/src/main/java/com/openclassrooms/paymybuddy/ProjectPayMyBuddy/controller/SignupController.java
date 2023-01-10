package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.UserDTO;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/newUser")
public class SignupController {

    Logger log = LoggerFactory.getLogger(UserController.class);


    private UserService userService = new UserService();


    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView(Model model) {
        model.addAttribute("user",new User());

        return "newUser";
    }

    @PostMapping
    public String newUser(@ModelAttribute("createUser") UserDTO user, RedirectAttributes redir){
        if(userService.getByEmail(user.getEmail()) == null) {
            userService.createUser(user);
            redir.addFlashAttribute("createUserSuccess", "OK");
            log.info("Success create user");
            return "redirect:/login";
        }

        return "login";
    }

}