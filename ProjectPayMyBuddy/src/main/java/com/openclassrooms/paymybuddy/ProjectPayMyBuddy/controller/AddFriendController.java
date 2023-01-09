package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/addFriend")
public class AddFriendController {

    @Autowired
    UserService userService;
    //A VOIR CAR PAS SURE !!
    Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public String friend(Model model, Principal user){
/**
        String userPrincipalEmail = userService.getUserMail();
        User user = new User();

        user = userService.getByEmail(userPrincipalEmail);
        List<User> contactsList = userService.getAllUser();

        model.addAttribute("contactList", contactsList);
        model.addAttribute("user", user);

        model.addAttribute("friendEmail", userPrincipalEmail);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("name", user.getName());
        model.addAttribute("wallet", user.getWallet());
        return "addFriend";

 **/
        User userConnected = userService.getByEmail(user.getName());
        List<User> listOfUser = userConnected.getContacts();
        model.addAttribute("users", listOfUser);
        return "addFriend";
    }

    @PostMapping
    public String addFriend(@RequestParam(name="emailtoadd") String email, Principal user, RedirectAttributes Redir) {
        //1 voir si l'utilisateur existe
        //if (userService.getByEmail(email) != null || userService.getByEmail(email) != null) {
        //}

        //on récupère les id
        User owner = userService.getByEmail(user.getName());
        User friend = userService.getByEmail(email);

        if (!owner.getContacts().contains(friend)) {
            userService.addFriend(owner, friend);
            log.info("Add friend SUCCES");
            Redir.addFlashAttribute("connectionsuccess", "OK");
            return "redirect:/addFriend";
        }else {
            Redir.addFlashAttribute("errorconnection","KO");
            log.info("ERROR, wrong email or contact already in your contactList " + friend);
        }
        return "redirect:/addFriend";
    }
/**
    @GetMapping
    public String getContactList(Model model, @AuthenticationPrincipal User user1){
        User user = new User();
         user = userService.getByEmail(user.getName());
        List<User> contactsList = user.getContactUserList();
        model.addAttribute("contactList", contactsList);
        model.addAttribute("user", user);

        return "addFriend";
    }

**/
}
