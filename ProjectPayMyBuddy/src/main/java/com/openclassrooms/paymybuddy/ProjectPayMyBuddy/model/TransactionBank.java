package com.openclassromms.paymybuddy.ProjectPayMyBuddy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction_bank")
public class TransactionBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_bank_id")
    private int transactionBankId;

    @ManyToOne
    private User user;

    @Column(name = "date")
    private Date date;

    @Column(name = "accountNumber")
    private double accountNumber;

    @Column(name = "account")
    private String account;

    @Column(name="comment")
    private String comment;

    @Column(name="amount")
    private float amount;


    public int getTransactionBankId() {
        return transactionBankId;
    }

    public void setTransactionBankId(int transactionBankId) {
        this.transactionBankId = transactionBankId;
    }

    public Date getDate(Date date) {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public double getAccountNumber(Long accountNumber) {
        return this.accountNumber;
    }

    public void setAccountNumber(double accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getComment(String comment) {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser(User user) {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
