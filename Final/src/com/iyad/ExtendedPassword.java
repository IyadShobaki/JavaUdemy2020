package com.iyad;

public class ExtendedPassword extends Password {
    private int decryptedPassword;

    public ExtendedPassword(int passwrod) {
        super(passwrod);
        this.decryptedPassword = passwrod;
    }

//    @Override
//    public void storePassword() {
//        System.out.println("Saving passwrod as "+ this.decryptedPassword);
//    }
}
