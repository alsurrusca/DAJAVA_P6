package com.openclassromms.paymybuddy.ProjectPayMyBuddy.DTO;

public class BankDto {

    private String iban;
    private float amount;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
