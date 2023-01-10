package com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository;


import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Integer> {
    List<BankAccount> findAll();
    Optional<BankAccount> findById(Integer id);
    BankAccount findByIban(String iban);
}
