package com.shobaki;


import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;
    private static int count;

    @org.junit.jupiter.api.BeforeAll
    public static void beforeClass() {
        System.out.println("This executes before any test cases. count = " + count++);
    }

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    @org.junit.jupiter.api.Test
    void deposit() {

        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);

    }

    @org.junit.jupiter.api.Test
    void withdraw_branch() {
//        fail("This test has yet to be implemented");
        double balance = account.withdraw(600, true);
        assertEquals(400.00, balance, 0);
    }

    @org.junit.jupiter.api.Test()
    void withdraw_notBranch() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(600, false);
        });

        //Another way
//        try{
//            account.withdraw(600, false);
//            fail("Should have thrown an IllegalArgumentException");
//        }catch(IllegalArgumentException e){
//
//        }

        //Another way
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//
//            double balance = account.withdraw(600.00, false);
//
//            assertEquals(400.00, account.getBalance(), 0);
//
//        });

        //  account.withdraw(600,false);

        //   assertEquals(400.00,balance,0);

    }

    @org.junit.jupiter.api.Test
    void getBalance_deposit() {

        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @org.junit.jupiter.api.Test
    void getBalance_withdraw() {

        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @org.junit.jupiter.api.Test
    public void isChecking_true() {

//        assertEquals(true,account.isChecking());
        assertTrue(account.isChecking(), "The account is NOT a checking account");
    }

    @org.junit.jupiter.api.AfterAll
    public static void AfterClass() {
        System.out.println("This executes after any test cases. count = " + count++);
    }

    @org.junit.jupiter.api.AfterEach
    public void teardown() {
        System.out.println("count = " + count++);
    }

}