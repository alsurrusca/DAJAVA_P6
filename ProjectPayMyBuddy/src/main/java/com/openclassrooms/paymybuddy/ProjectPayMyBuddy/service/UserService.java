package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.EmailDTO;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.IdentifyDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.UserDTO;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    /**
    public boolean login(LoginDto loginDto){
        User user = getByEmail(loginDto.getEmail()).get();
        if(loginDto.getPassword().equals(user.getPassword())){
            userRepository.save(user);
            return true;
        }
        return false;
    }

**/

    /**
     * Create new User
     * @param userDTO
     * @return new User
*/
    public User createUser(UserDTO userDTO){
        //String hashedPassword = encoder.encode(userDTO.getPassword());
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setName(userDTO.getName());
        user.setWallet(userDTO.getWallet());

        return userRepository.save(user);
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
        User user = userRepository.findByEmail(userDTO.getOldEmail());
        if(user != null){
            if(user.getEmail().equals(userDTO.getOldEmail())){
                user.setEmail(userDTO.getNewEmail());
                userRepository.save(user);
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
        User user = userRepository.findByEmail(identifyDto.getEmail());
        if(user != null){
                        if(identifyDto.getOldPassword().equals(user.getPassword())){
                user.setPassword(identifyDto.getNewPassword());
                return true;
            }
        }
        return false;
    }

    public void addFriend(User owner, User contact){
        owner.addContact(contact);
        userRepository.save(owner);

    }

    public Optional<User> getById(Integer id){
        return userRepository.findById(id);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getByEmail(String email) {
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

public static String getUserMail() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    String userName = null;
    if (authentication != null) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userName = userDetails.getUsername();

    }
    //log.debug("Negative Balance exception trigerred");
    return userName;
}

}
