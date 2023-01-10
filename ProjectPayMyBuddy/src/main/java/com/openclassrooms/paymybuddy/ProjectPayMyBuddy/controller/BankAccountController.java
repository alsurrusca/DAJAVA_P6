package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.BankAccountService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/newBankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(BankAccountController.class);

    @GetMapping
    public String bankAccount(Model model, Principal user) {
        User userConnected = userService.getByEmail(user.getName());
        List<BankAccount> bankAccountList = userConnected.getBankAccountList();
        model.addAttribute("bankAccountList", bankAccountList);
        return "newBankAccount";
    }

    @PostMapping
    public String newBankAccount(Principal user, BankAccount bankAccount, RedirectAttributes redir) {
        User userConnected = userService.getByEmail(user.getName());
        BankAccount bankAccountExisting = bankAccountService.getBankAccountByIban(bankAccount.getIban());

        if (userConnected.getBankAccountList().contains(bankAccountExisting)) {
            log.error("Iban already exist");
            redir.addFlashAttribute("errorbankaccount", "KO");
            return "redirect:/newBankAccount";
        } else {
            redir.addFlashAttribute("bankaccountsuccess", "OK");
            bankAccountService.addBankAccount(userConnected, bankAccount.getName(), bankAccount.getIban());
            return "redirect:/newBankAccount";
        }
    }

}
