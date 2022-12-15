package com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <User,Integer> {


    Optional<User> findByEmail(String email);

}
