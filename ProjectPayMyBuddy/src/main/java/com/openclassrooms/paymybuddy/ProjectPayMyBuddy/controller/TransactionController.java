package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;


import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.TransactionService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(TransactionController.class);


    @GetMapping
    public String transfer(Model model, Principal user){

        User userConnected = userService.getByEmail(user.getName());
        List<Transaction> listTransaction = userConnected.getTransactions();
        User userFriend = userService.getByEmail(user.getName());
        List<User> listOfUser = userFriend.getContacts();
        model.addAttribute("users", listOfUser);
        model.addAttribute("listTransaction", listTransaction);

        return "transfer";
    }

    /**
     *
     * @param user
     * @param debiteur
     * @param amount
     * @param comment
     * @param Redir
     * @return tranfer - SEND MONEY SUCCESS
     */

    @PostMapping
    public String sendMoney(Principal user, String debiteur, float amount, String comment, RedirectAttributes Redir) {

        User owner = userService.getByEmail(user.getName());

        if (amount > 0 && amount <= owner.getWallet()) {
            transactionService.makePayment(owner, debiteur, comment, amount);
            Redir.addFlashAttribute("transactionsuccess", "OK");
            return "redirect:/transfer";
        } else {
            log.error("Error amount");
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/transfer";
        }
    }




}
