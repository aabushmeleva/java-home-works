package ru.qiwi.payments.dto;

public class Payment {
    private final int amount;

    public Payment(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotalSum() {
        return amount;
    }
}
