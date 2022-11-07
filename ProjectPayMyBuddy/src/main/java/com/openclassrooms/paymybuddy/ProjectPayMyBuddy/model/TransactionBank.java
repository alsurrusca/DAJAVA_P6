package com.openclassrooms.paymybuddy.ProjectPayMyBuddy.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "value")
    private Float value;

    @Column(name="comment")
    private String comment;



    public int getTransactionBankId() {
        return transactionBankId;
    }

    public void setTransactionBankId(int transactionBankId) {
        this.transactionBankId = transactionBankId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
