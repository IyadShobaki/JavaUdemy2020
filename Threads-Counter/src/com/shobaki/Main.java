package com.shobaki;

public class Main {

    public static void main(String[] args) {

        Countdown countdown = new Countdown();
        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

//        Countdown countdown1 = new Countdown();
//        Countdown countdown2 = new Countdown();
//
//        CountdownThread t1 = new CountdownThread(countdown1);
//        t1.setName("Thread 1");
//        CountdownThread t2 = new CountdownThread(countdown2);
//        t2.setName("Thread 2");
//
//        t1.start();
//        t2.start();
    }

}

//we can synchronized the methods but not the constructor
class Countdown {

    private int i;

    public void doCountdown() {
        //to prevent a race condition we can synchronized the entire method or synchronize a block of statements
//    public synchronized void doCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;

        }
        //using color - local variable will not solve the race condition
//        synchronized (color) {
//            for (i = 10; i > 0; i--) {
//                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
//            }
//        }
        //using this will solve the race condition
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }

    }
}

class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }
}