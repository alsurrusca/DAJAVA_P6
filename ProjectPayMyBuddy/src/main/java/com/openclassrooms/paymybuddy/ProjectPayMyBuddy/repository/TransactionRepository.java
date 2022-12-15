package com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends CrudRepository <Transaction, Integer> {

}