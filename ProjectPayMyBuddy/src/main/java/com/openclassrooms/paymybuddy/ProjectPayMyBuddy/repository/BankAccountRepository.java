package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.repository;

import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount,Integer> {
}
