package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUser(){

     return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById (Integer id){
        return userRepository.findById(id);
    }
}
