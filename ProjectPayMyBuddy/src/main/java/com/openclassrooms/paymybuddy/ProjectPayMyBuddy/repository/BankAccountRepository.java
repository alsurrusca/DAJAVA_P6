package com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository;


import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Integer> {
    List<BankAccount> findAllByUser(User user);
}
