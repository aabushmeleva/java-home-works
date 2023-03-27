package ru.qiwi.accounts;

public abstract class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    protected abstract double getMoneyWithCommission(double money);

    protected abstract double getCommission();

    public double getAmount() {
        return balance;
    }

    public void setAmount(double amount) {
        this.balance = amount;
        System.out.println("Balance was updated: " + amount);
    }

    public void getMoney(double money) {
        double prospectiveAmount = getAmount() - getMoneyWithCommission(money);
        if (prospectiveAmount >= 0) {
            setAmount(prospectiveAmount);
        } else {
            notEnoughMoney();
        }
    }

    public void notEnoughMoney() {
        System.out.println("Not enough money for operation");
    }

}
