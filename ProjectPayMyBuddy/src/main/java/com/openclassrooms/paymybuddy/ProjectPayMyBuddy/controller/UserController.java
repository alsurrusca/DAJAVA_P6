package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;


import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.UserDTO;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService = new UserService();

    @Autowired
    private ModelMapper modelMapper;

    Logger log = LoggerFactory.getLogger(UserController.class);


    @GetMapping({"/login","/"})
    public String login(){return "login";}

    /**
     *
     * @param model
     * @return home
     */

    @GetMapping("/home")
    public String home(Model model) {
        String userPrincipalEmail = UserService.getUserMail();
        User user = new User();

        user = userService.getByEmail(userPrincipalEmail);

        model.addAttribute("username", userPrincipalEmail);
        model.addAttribute("firstName", user.getFirstName());

        return "home";
    }

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


}

