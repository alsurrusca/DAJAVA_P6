package com.openclassromms.paymybuddy.ProjectPayMyBuddy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transaction_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User debiteur;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_user_id")
    private User crediteur;

    @Column(name = "date")
    private Date date;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "comment")
    private String comment;


    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getDebiteur() {
        return debiteur;
    }

    public void setDebiteur(User debiteur) {
        this.debiteur = debiteur;
    }

    public User getCrediteur() {
        return crediteur;
    }

    public void setCrediteur(User crediteur) {
        this.crediteur = crediteur;
    }
}
