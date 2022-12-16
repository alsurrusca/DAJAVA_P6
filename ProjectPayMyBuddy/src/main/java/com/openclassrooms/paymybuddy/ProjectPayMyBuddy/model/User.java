package com.openclassromms.paymybuddy.ProjectPayMyBuddy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "wallet")
    private double wallet;


    @OneToMany(
            cascade = CascadeType.ALL,
            //On ne veut pas de transaction orpheline si l'utilisateur supprime son compte
            orphanRemoval = true,
            //on récupère toutes les transactions d'un USER
            fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_id")
    List<Transaction> transactions = new ArrayList<>();


    @ManyToMany(
            //faire référence à l'attribut dans la seconde entité
            cascade = CascadeType.ALL

    )
    @JoinColumn(name = "user_id")
    private List<User> contacts = new ArrayList<>();


    public void addContact(User user) {
        //ajout du contact
        contacts.add(user);
        //Chercher la liste de contact et ajouter l'objet
        user.getContacts().add(this);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }
}
