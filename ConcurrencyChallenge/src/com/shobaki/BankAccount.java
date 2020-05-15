package com.shobaki;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//synchronized using using the lock() method. We can use it because both methods
//compete for the same lock!!

public class BankAccount {
    private String accountNumber;
    private double balance;
    private Lock lock;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public void deposit(double amount) {
        boolean status = false;

        //use tryLock() method
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(balance);
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }

        } catch (InterruptedException e) {
            // do something here
        }

        System.out.println("Transaction status = " + status);
        //using lock() method
//            lock.lock();
//            try{
//                System.out.println(balance);
//                balance += amount;
//            }finally {
//                lock.unlock();
//            }


    }

    public void withdraw(double amount) {

        boolean status = false;
        //use tryLock() method
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(balance);
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }

        } catch (InterruptedException e) {
            // do something here
        }

        System.out.println("Transaction status = " + status);

        //using lock() method
//        lock.lock();
//        try{
//            System.out.println(balance);
//            balance -= amount;
//        }finally {
//            lock.unlock();
//        }

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber);
    }

    public double getBalance() {
        return balance;
    }
}

//first two synchronized ways
//public class BankAccount {
//    private String accountNumber;
//    private double balance;
//
//    public BankAccount(String accountNumber, double balance) {
//        this.accountNumber = accountNumber;
//        this.balance = balance;
//    }
//
//    //way to synchronized transactions
////    public synchronized void deposit(double amount){
////        balance += amount;
////    }
////    public synchronized void withdraw(double amount){
////        balance -= amount;
////    }
//
//    //another way
//    public void deposit(double amount) {
//        synchronized (this){
//            System.out.println(balance);
//            balance += amount;
//        }
//
//    }
//
//    public void withdraw(double amount) {
//        synchronized (this){
//            System.out.println(balance);
//            balance -= amount;
//        }
//
//    }
//
//    public String getAccountNumber() {
//        return accountNumber;
//    }
//
//    public void printAccountNumber(){
//        System.out.println("Account number = " + accountNumber);
//    }
//    public double getBalance() {
//        return balance;
//    }
//}
