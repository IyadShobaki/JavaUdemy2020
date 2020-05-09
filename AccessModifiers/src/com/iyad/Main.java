package com.iyad;

public class Main {

    public static void main(String[] args) {
        Account iyadAccount  = new Account("Iyad");
        iyadAccount.deposit(1000);
        iyadAccount.withdraw(500);
        iyadAccount.withdraw(-200);
        iyadAccount.deposit(-20);
        iyadAccount.calculateBalance();
        iyadAccount.balance = 5000;

        System.out.println("Balance on account is " + iyadAccount.getBalance());
        iyadAccount.transactions.add(4500);
        iyadAccount.calculateBalance();

    }
}
