package com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@EntityScan
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAll();
    Optional<Transaction> findById(Integer id);
    Transaction save(Transaction transaction);

}