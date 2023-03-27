package ru.qiwi.accounts;

public class PremiumAccount extends BankAccount{

    public PremiumAccount(double balance) {
        super(balance);
    }

    @Override
    protected double getMoneyWithCommission(double money) {
        return money + getCommission();
    }

    @Override
    protected double getCommission() {
        return 1;
    }
}
