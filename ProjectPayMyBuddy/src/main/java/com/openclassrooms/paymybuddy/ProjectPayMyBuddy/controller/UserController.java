package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/getUser")
    public List<User> getUser() {
        List<User> listOfUser = userService.getUser();
        if (listOfUser.isEmpty()) {
            log.error("FAILED");
        } else {
            log.info("Success find user");

        } return listOfUser;

    }
}
