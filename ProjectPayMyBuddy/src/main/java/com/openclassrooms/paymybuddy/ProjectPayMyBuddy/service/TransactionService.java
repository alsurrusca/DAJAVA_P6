package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.TransactionRepository;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {


    @Autowired
    private UserService userService;

    private static final float FEE = 0.005f;

    @Autowired
    private TransactionRepository transactionRepository;


        public List<Transaction> getAllTransaction(){
            return transactionRepository.findAll();
        }

        public List<Transaction> findAll(){
        return transactionRepository.findAll();
        }

        public Optional<Transaction> getTransactionById(Integer id){
        return transactionRepository.findById(id);
        }

    public void makePayment(User userCreditor, String debitor, String comment, float amount) {
        User userDebiteur = userService.getByEmail(debitor);
        LocalDateTime inTime = LocalDateTime.now();
        float fee = amount * FEE;

        if (userCreditor.getWallet() < amount || amount < 0) {
            System.out.println("Transfer cannot be made");
        } else {
            Transaction transaction = new Transaction(userCreditor, userDebiteur, amount, inTime, comment, fee);

            userCreditor.setWallet(userCreditor.getWallet() - amount);
            userDebiteur.setWallet(userDebiteur.getWallet() + (amount - fee));
            transactionRepository.save(transaction);
        }
    }


    }

