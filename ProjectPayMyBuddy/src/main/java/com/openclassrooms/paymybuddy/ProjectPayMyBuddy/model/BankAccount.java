package com.openclassromms.paymybuddy.ProjectPayMyBuddy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "iban")
    private String iban;

    @OneToMany(cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<TransactionBank> transactionBankList = new ArrayList<>();

    public BankAccount(){}

    public BankAccount (int id, User user, String name, String iban){
        this.id = id;
        this.user = user;
        this.name = name;
        this.iban = iban;
    }

    public BankAccount (User user, String name, String iban) {
        this.user = user;
        this.name = name;
        this.iban = iban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }


}