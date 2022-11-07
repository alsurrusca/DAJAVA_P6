package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.repository;

import com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model.TransactionBank;
import org.springframework.data.repository.CrudRepository;

public interface TransactionBankRepository extends CrudRepository <TransactionBank, Integer> {
}
