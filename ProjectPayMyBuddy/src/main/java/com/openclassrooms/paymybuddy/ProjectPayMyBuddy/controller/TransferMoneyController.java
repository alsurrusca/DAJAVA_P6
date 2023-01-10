package com.openclassromms.paymybuddy.ProjectPayMyBuddy.controller;

import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.BankAccount;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.model.User;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.BankAccountService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.TransactionBankService;
import com.openclassromms.paymybuddy.ProjectPayMyBuddy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/transferMoney")
public class TransferMoneyController {

    @Autowired
    UserService userService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    TransactionBankService transactionBankService;

    Logger log = LoggerFactory.getLogger(TransactionBankController.class);


    @GetMapping
    public String transactionBank(Model model, Principal user) {

        User userConnected = userService.getByEmail(user.getName());

        List<BankAccount> bankAccountList = userConnected.getBankAccountList();
        model.addAttribute("bankAccountList", bankAccountList);

        return "transferMoney";
    }

    /**
     *
     * @param user
     * @param amount
     * @return tranferMoney - ADD Money SUCCESS
     */

    @PostMapping
    public String transferMoneyToSite(Principal user, float amount, RedirectAttributes redir) {

        User userConnected = userService.getByEmail(user.getName());
            transactionBankService.transferToSite(userConnected, amount);
            redir.addFlashAttribute("banktransactionsuccess", "OK");
            log.info("Deposit Money to Bank Account SUCCESS ");
            return "redirect:/transferMoney";
        }

}
