package com.shobaki;

import static com.shobaki.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread{

    @Override
    public void run() {
//        System.out.println(ANSI_BLUE + "Hello from another thread.");
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println(ANSI_BLUE + "Another thread woke me up");
            return;
        }

        System.out.println(ANSI_BLUE + "Three seconds have passed and I'm awake");

    }
}
