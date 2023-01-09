package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.TransactionDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.TransactionRepository;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static final float FEE = 0.005f;

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

    public void makePayment(User userCreditor, String userDebtorEmail, String comment, float amount) {
        User userDebiteur = userService.getByEmail(userDebtorEmail);
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
