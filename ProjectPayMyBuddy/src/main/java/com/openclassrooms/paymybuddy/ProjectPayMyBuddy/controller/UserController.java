package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.EmailDTO;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.IdentifyDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.LoginDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.UserDTO;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.UserRepository;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService = new UserService();

    @Autowired
    private ModelMapper modelMapper;

    Logger log = LoggerFactory.getLogger(UserController.class);

    //Que dois je faire : login, logout, inscription, ajouter un user, nouvel utilisateur, change password et email


    @GetMapping({"/login","/"})
    public String login(){return "login";}


    @GetMapping("/home")
    public String home(Model model) {
        String userPrincipalEmail = userService.getUserMail();
        User user = new User();

        user = userService.getByEmail(userPrincipalEmail);

        model.addAttribute("username", userPrincipalEmail);
        model.addAttribute("firstName", user.getFirstName());

        return "home";
    }
/**
    @GetMapping("/personList")
    public String getAllUser(Model model) {
        List<User> listOfUser = userService.getAllUser();
        model.addAttribute("users", listOfUser);
        return "personList";
    }

**/
    /**
     * Get user by Email
     * @param email
     * @return Bad Requesti if user dosn't exist
     */
    @GetMapping(value = "/getUser")
    public ResponseEntity<UserDTO> getByEmail(@RequestParam("email") String email){
        User user = userService.getByEmail(email);
        if(user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Success - Find User by Email");
        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }


    @PutMapping(value = "/passwordModify")
    public ResponseEntity modifyPassword(@RequestBody IdentifyDto modifyPassword){
        if(userService.getByEmail(modifyPassword.getEmail()) != null) {
            if(userService.changePassword(modifyPassword)){
                log.info("Password was updated with SUCCESS");
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/emailModify")
    public ResponseEntity modifyEmail(@RequestBody EmailDTO modifyEmail){
        if(userService.getByEmail(modifyEmail.getOldEmail()) != null) {
            if(userService.updateMail(modifyEmail)){
                log.info("Email modify with SUCCES");
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

}

