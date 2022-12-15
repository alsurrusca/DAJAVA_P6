package com.openclassromms.paymybuddy.ProjectPayMyBuddy.service;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO.BankDto;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    /**
     * Create a new BankAccount
     * @param accountNumber = numero IBAN
     * @param amount = Transfered money
     * @return the object of BankAccount
     */
    public boolean createBankAccount(User user, String accountNumber, float amount){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUser(user);
        bankAccount.setIban(accountNumber);
        bankAccount.setAmount(amount);
        bankAccountRepository.save(bankAccount);
        return true;
    }

    /**
     * Find all bankAccount by user
     * @param user
     * @return list of bankAccount
     */
    public List<BankAccount> listOfBankAccountByUser(User user){
        return bankAccountRepository.findAllByUser(user);
    }


}
