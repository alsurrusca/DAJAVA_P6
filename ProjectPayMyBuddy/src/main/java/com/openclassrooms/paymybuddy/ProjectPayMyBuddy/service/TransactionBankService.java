package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.TransactionBank;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.TransactionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransactionBankService {

    @Autowired
    private TransactionBankRepository transactionBankRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BankAccountService bankAccountService;
//Transaction entre un USER et sa banque


    public List<TransactionBank> getAllBankTransactions() {
        return transactionBankRepository.findAll();
    }

    public TransactionBank addBankTransaction(TransactionBank bankTransaction) {
        return transactionBankRepository.save(bankTransaction);
    }

    public void depositToBank(User user, float amount) {
        User userConnected = userService.getByEmail(user.getEmail());

        if (userConnected.getWallet() < amount || amount < 0) {
            System.out.println("Transfer cannot be made");
        } else {
            TransactionBank transaction = new TransactionBank(user,amount);

            userConnected.setWallet(userConnected.getWallet() - amount);
            transactionBankRepository.save(transaction);
        }
    }

    public void transferToSite(User user, float amount) {

        User userConnected = userService.getByEmail(user.getEmail());
        TransactionBank transaction = new TransactionBank(user, amount);
        userConnected.setWallet(userConnected.getWallet() + amount);
        transactionBankRepository.save(transaction);


    }
}

