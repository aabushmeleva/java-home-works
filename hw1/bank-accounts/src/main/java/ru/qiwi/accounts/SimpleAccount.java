package ru.qiwi.accounts;

public class SimpleAccount extends BankAccount {

    public SimpleAccount(double balance) {
        super(balance);
    }

    @Override
    protected double getMoneyWithCommission(double money) {
        System.out.println(getCommission());
        return money * (1 + getCommission());
    }
    @Override
    protected double getCommission() {
        return 0.05;
    }
}