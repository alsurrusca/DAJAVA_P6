package com.openclassrooms.paymybuddy.ProjectPayMyBuddy;

import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class ProjectPayMyBuddyApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectPayMyBuddyApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception{

		Optional<User> optionalUser = userService.getUserById(1);
		User user1 = optionalUser.get();
		System.out.println(user1.getName());

		user1.getTransactions().forEach(
			transaction -> System.out.println(transaction.getComment())
		);

	}

}
