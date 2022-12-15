package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.BankDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.TransactionBank;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.BankAccountService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.TransactionBankService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.TransactionService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class TransactionBankController {

    private UserService userService;
    private TransactionBankService bankService;
    private BankAccountService bankAccountService;

    Logger log = LoggerFactory.getLogger(TransactionBankController.class);

    // Nouvelle transaction,

    @PostMapping(value = "bankTransaction")
    public ResponseEntity<BankDto> transaction(@RequestBody BankDto bankDto, @PathVariable Integer userId) {
        if (userService.getById(userId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user = userService.getById(userId).get();
        TransactionBank bankAccount = bankService.createTransactionBank(user, bankDto.getIban(), bankDto.getAmount());
        if (bankService.processTransaction(bankAccount)) {
            bankService.save(bankAccount);
            log.info("Create new Bank Transaction Success");
            return ResponseEntity.ok(bankDto);
        } else {
            log.info("Invalid ID, transaction FAILED");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping(value="bankTransaction")
    public ResponseEntity<List<TransactionBank>> getAllTransaction(@PathVariable Integer userId){
        if(userService.getById(userId).isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user = userService.getById(userId).get();
        List<TransactionBank> list = bankService.findAllTransactionByUser(user);
        return ResponseEntity.ok(list);
    }
}
