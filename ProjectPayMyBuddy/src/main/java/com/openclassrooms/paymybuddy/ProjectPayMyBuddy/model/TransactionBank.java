package com.openclassromms.paymybuddy.ProjectPayMyBuddy.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "transaction_bank")
public class TransactionBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_bank_id")
    private int transactionBankId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="bankaccount_id")
    private BankAccount bankAccount;
    @Column(name = "amount")
    private float amount;

    @Column(name = "inTime")
    private LocalDateTime inTime;

    @Column(name = "operation_type")
    private String operationType;


    public TransactionBank(){}

    public TransactionBank(User user, float amount) {
        this.user = user;
        this.amount = amount;
    }


    public int getTransactionBankId() {
        return transactionBankId;
    }

    public void setTransactionBankId(int transactionBankId) {
        this.transactionBankId = transactionBankId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
