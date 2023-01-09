package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.TransactionDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.Transaction;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.TransactionService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(TransactionController.class);

    //Make transaction
    @GetMapping
    public String transfer(){
        return "transfer";
    }

    @PostMapping
    public String sendMoney(@AuthenticationPrincipal UserPrincipal user, String userDebtorEmail,
                            float amount, String description, RedirectAttributes Redir) {
        User userConnected = userService.getByEmail(user.getName());


        if (amount > 0 && amount <= userConnected.getWallet()) {
            transactionService.makePayment((User) user, userDebtorEmail, description, amount);
            Redir.addFlashAttribute("transactionsuccess", "OK");
            return "redirect:/transfer";
        } else {
            log.error("Error amount");
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/transfer";
        }
    }


}
