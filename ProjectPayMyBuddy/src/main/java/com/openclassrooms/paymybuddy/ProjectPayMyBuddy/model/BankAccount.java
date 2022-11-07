package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model;

import jdk.jfr.Enabled;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private int bankAccountId;

    @ManyToOne
    private User user;

    @ManyToOne
    private BankAccount bankAccount;

    @Column(name = "iban")
    private long iban;

    @Column(name = "date")
    private Date date;

    @Column(name = "comment")
    private String comment;

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public long getIban() {
        return iban;
    }

    public void setIban(long iban) {
        this.iban = iban;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
