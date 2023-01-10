package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(BankAccountService.class);


    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }


    public void addBankAccount(User user, String name, String iban){
        User userConnected = userService.getByEmail(user.getEmail());

        if(bankAccountRepository.findByIban(iban) == null){
        log.info("new Bank account add SUCCESS");
        BankAccount bankAccount = new BankAccount(userConnected, name, iban);
        bankAccountRepository.save(bankAccount);

    } else {
            log.error("bankAccount already exist");
        }
    }

    public BankAccount getBankAccountByIban(String iban){
        return bankAccountRepository.findByIban(iban);
    }

}
