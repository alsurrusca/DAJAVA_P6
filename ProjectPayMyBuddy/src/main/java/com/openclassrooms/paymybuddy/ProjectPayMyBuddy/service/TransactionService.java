package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.TransactionDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.TransactionRepository;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Check payment - User1 has enough money and have contact
     *
     * @param fromUser - User who send money
     * @param toUser   - User receiving money
     * @param total    - the amount + fee
     * @return true = authorized | false = not authorizes
     */
    private boolean paymentAuthorized(User fromUser, User toUser, double total) {
        return fromUser.getWallet() > total && fromUser.getContacts().contains(toUser);
    }

    public boolean makePayment(TransactionDto transactionDto) {
        if (userRepository.findById(transactionDto.getSendId()).isEmpty() || userRepository.findById(transactionDto.getReceiveId()).isEmpty()
                || transactionDto.getAmount() <= 0) {
            return false;
        }

        // Fee of PayMyBuddy = 5% of transaction
        double amount = transactionDto.getAmount();
        double fee = amount * 0.5;
        double total = amount + fee;

        User fromUser = userRepository.findById(transactionDto.getSendId()).get();
        User toContact = userRepository.findById(transactionDto.getReceiveId()).get();

        if (paymentAuthorized(fromUser, toContact, total)) {
            fromUser.setWallet(fromUser.getWallet() - total);
            toContact.setWallet(toContact.getWallet() + amount);
            Transaction transaction = new Transaction();
            transaction.setDebiteur(fromUser);
            transaction.setCrediteur(toContact);
            transaction.setCost(amount);
            transaction.setDate(new Date());
            transaction.setComment(transactionDto.getComment());
            transactionRepository.save(transaction);
            userRepository.save(fromUser);
            userRepository.save(toContact);
            return true;
        }
        return false;
    }


}
