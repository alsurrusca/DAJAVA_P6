package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.repository;

import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Access;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {


}
