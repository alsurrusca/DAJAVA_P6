package com.openclassromms.paymybuddy.ProjectPayMyBuddy.model;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime date;

    @Column(name = "cost")
    private float cost;

    @Column(name = "comment")
    private String comment;

    @Column(name = "fee")
    private float fee;

    public Transaction(User userCreditor, User userDebiteur, float amount, LocalDateTime inTime, String comment, float fee) {
        this.crediteur = userCreditor;
        this.debiteur = userDebiteur;
        this.cost = amount;
        this.date = inTime;
        this.comment = comment;
        this.fee = amount * FEE;
    }

    private static final float FEE = 0.005f;

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public static float getFEE() {
        return FEE;
    }
}