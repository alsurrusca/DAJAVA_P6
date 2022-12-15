package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.TransactionBank;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.TransactionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionBankService {

    @Autowired
    private TransactionBankRepository transactionBankRepository;

    @Autowired
    private UserService userService;

//Transaction entre un USER et sa banque



    public boolean processTransaction(TransactionBank bankService) {
        //On regarde si il a de l'argent ou non
        if (bankService.getAmount() == 0) {
            return false;
        }

        User user = bankService.getUser();
        //la valeur absolu du compte
        double amount = Math.abs(bankService.getAmount());

        if (bankService.getAmount() > 0) {
            user.setWallet(user.getWallet() + amount);
        } else if (bankService.getAmount() < 0 && amount <= user.getWallet()) {
            user.setWallet(user.getWallet() - amount);
        } else {
            return false;
        }

        userService.saveUser(user);
        return true;
    }

    public List<TransactionBank> findAllTransactionByUser(User user) {
        return transactionBankRepository.findAllByUser(user);
    }

    public TransactionBank save(TransactionBank bank){
        return transactionBankRepository.save(bank);
    }

    public TransactionBank createTransactionBank(User user, String iban, float amount) {
        TransactionBank transaction = new TransactionBank();
        transaction.setUser(user);
        transaction.setAccountNumber(Double.parseDouble(iban));
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transactionBankRepository.save(transaction);
        return transaction;
    }
}
