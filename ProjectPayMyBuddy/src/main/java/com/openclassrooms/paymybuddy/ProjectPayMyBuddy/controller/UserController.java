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


    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping({"/home","/"})
    public String home() {
        return "home";
    }

    @GetMapping("/personList")
    public String getAllUser(Model model) {
        List<User> listOfUser = userService.getAllUser();
        model.addAttribute("users", listOfUser);
        return "personList";
    }

    /**
     * Create new User
     * @param user
     * @return 200 if it's ok / 400 if failed
     */
    @RequestMapping("/newUser")
    @PostMapping
    private String signupUser(@ModelAttribute UserDTO user, Model model, RedirectAttributes redirAttrs) {
        String signupError = null;
        Optional<User> existsUser = userService.getByEmail(user.getEmail());
        if (existsUser != null) {
            signupError = "The email already exists";
        }
        if (signupError == null) {
            userService.createUser(user);
        }

        if (signupError == null) {
            log.info("Success - Create User");
            redirAttrs.addFlashAttribute("message", "You've successfully signed up, please login.");
            return "login";
        } else {
            model.addAttribute("signupError", true);
        }

        return "newUser";

    }


    /**
     * Get user by Email
     * @param email
     * @return Bad Requesti if user dosn't exist
     */
    @GetMapping(value = "/getUser")
    public ResponseEntity<UserDTO> getByEmail(@RequestParam("email") String email){
        Optional<User> user = userService.getByEmail(email);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Success - Find User by Email");
        return ResponseEntity.ok(modelMapper.map(user.get(), UserDTO.class));
    }

    /**
     * Login user
     * @param loginDto - user's email & password
     * @return 200 if it's ok / 400 if failed
     */

    @PostMapping(value="/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        if(userService.getByEmail(loginDto.getEmail()).isPresent()){
            if(userService.login(loginDto)){
                log.info("Connection Success");
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    //A VOIR CAR PAS SURE !!
    @PutMapping(value = "/addFriend")
    public ResponseEntity addFriend(@PathVariable Integer user, @PathVariable Integer contact) {
        //1 voir si l'utilisateur existe
        if(userService.getById(user).isEmpty() || userService.getById(contact).isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //on récupère les id
        User owner = userService.getById(user).get();
        User friend = userService.getById(contact).get();

        if(!owner.getContacts().contains(friend)){
            userService.addFriend(owner,friend);
            log.info("Add friend SUCCES");
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping(value = "/passwordModify")
    public ResponseEntity modifyPassword(@RequestBody IdentifyDto modifyPassword){
        if(userService.getByEmail(modifyPassword.getEmail()).isPresent()){
            if(userService.changePassword(modifyPassword)){
                log.info("Password was updated with SUCCESS");
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/emailModify")
    public ResponseEntity modifyEmail(@RequestBody EmailDTO modifyEmail){
        if(userService.getByEmail(modifyEmail.getOldEmail()).isPresent()){
            if(userService.updateMail(modifyEmail)){
                log.info("Email modify with SUCCES");
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }


}

