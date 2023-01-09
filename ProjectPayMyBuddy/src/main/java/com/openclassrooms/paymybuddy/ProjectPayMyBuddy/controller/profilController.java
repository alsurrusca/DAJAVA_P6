package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profil")
public class profilController {

    @Autowired
    UserService userService;


    @GetMapping
    public String getProfil(Model model){

        String userPrincipalEmail = userService.getUserMail();
        User user = new User();

        user = userService.getByEmail(userPrincipalEmail);

        model.addAttribute("username", userPrincipalEmail);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("name", user.getName());
        model.addAttribute("wallet", user.getWallet());
        model.addAttribute("contact", user.getContacts(user.getFirstName(),user.getName()));


        return "profil";
    }


}
