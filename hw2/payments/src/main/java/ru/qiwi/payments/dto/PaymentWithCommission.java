package ru.qiwi.payments.dto;

public class PaymentWithCommission extends Payment {

    private final int commission;

    public PaymentWithCommission(int amount, int commission) {
        super(amount);
        this.commission = commission;
    }

    public int getCommission() {
        return commission;
    }

    public int getTotalSum() {
        return super.getAmount() + getCommission();
    }
}