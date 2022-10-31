package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "debiteur")
    private User debiteur; //le User qui paye

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "crediteur")
    private User crediteur; //Le User qui reçoit

    @Column(name = "date")
    private Date date;

    @Column(name = "cost")
    private float coast;

    @Column(name = "comment")
    private String comment;

    @Column(name = "charge")
    private float charge;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getCoast() {
        return coast;
    }

    public void setCoast(float coast) {
        this.coast = coast;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
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
