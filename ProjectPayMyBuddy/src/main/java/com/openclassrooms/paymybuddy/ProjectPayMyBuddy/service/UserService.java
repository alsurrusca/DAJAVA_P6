package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.EmailDTO;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.IdentifyDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.LoginDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.UserDTO;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;
    
    public boolean login(LoginDto loginDto){
        User user = getByEmail(loginDto.getEmail()).get();
        if(loginDto.getPassword().equals(user.getPassword())){
            userRepository.save(user);
            return true;
        }
        return false;
    }



    /**
     * Create new User
     * @param user
     * @return new User
*/
    public UserDTO createUser(UserDTO user){
        //String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setFirstName(user.getFirstName());
        user.setName(user.getName());
        user.setWallet(user.getWallet());
        return user;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Update Email
     * @param userDTO
     * return new email
     */

    public boolean updateMail(EmailDTO userDTO){
        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getOldEmail());
        if(optionalUser.isPresent()){
            User userInDB = optionalUser.get();
            if(userInDB.getEmail().equals(userDTO.getOldEmail())){
                userInDB.setEmail(userDTO.getNewEmail());
                userRepository.save(userInDB);
                return true;
            }
        }
        return false;
    }

    /**
     * Change password
     * @param identifyDto with new and old password
     * @return new password
     */
    public boolean changePassword(IdentifyDto identifyDto){
        Optional<User>optionalUser = userRepository.findByEmail(identifyDto.getEmail());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(identifyDto.getOldPassword().equals(user.getPassword())){
                user.setPassword(identifyDto.getNewPassword());
                return true;
            }
        }
        return false;
    }

    public void addFriend(User owner, User contact){
        owner.getContacts().add(contact);
        userRepository.save(owner);
    }

    public Optional<User> getById(Integer id){
        return userRepository.findById(id);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

/**
    public User saveUser(UserDTO user) {
        User user1 = new User();
        user1.setFirstName(user.getFirstName());
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(encoder.encode(user.getPassword()));
        user1.setWallet(user.getWallet());
        return userRepository.save(user1);
    }

 **/
}
