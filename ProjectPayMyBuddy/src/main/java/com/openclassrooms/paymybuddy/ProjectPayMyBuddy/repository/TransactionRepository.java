package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.repository;

import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
