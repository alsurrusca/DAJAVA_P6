package com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.TransactionBank;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionBankRepository extends CrudRepository <TransactionBank, Integer> {
    List<TransactionBank> findAllByUser(User user);
}
