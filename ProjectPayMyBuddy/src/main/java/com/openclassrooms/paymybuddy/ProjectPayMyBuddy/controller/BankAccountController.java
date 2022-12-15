package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.BankDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.BankAccountService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(BankAccountController.class);


    @PostMapping(value = "/newBankAccount")
    public ResponseEntity<BankAccount> newBankAccount (@PathVariable User user, @PathVariable String accountNumber, @PathVariable float amount){
    if(bankAccountService.listOfBankAccountByUser(user).isEmpty()){
        bankAccountService.createBankAccount(user, accountNumber, amount);
        return ResponseEntity.ok().build();
    } else {
        log.info("Count exist");
        return ResponseEntity.badRequest().build();
    }
    }
}
