package com.iyad;

public class Password {
    private static final int key = 748576362;
    private final int encryptedPassword;

    public Password(int passwrod){
        this.encryptedPassword = encryptDecrypt(passwrod);
    }
    private int encryptDecrypt(int password){
        return password ^ key;

    }
    public final void storePassword(){
        System.out.println("Saving password as " + this.encryptedPassword);
    }
    public boolean letMeIn(int passwrod){
        if(encryptDecrypt(passwrod) == this.encryptedPassword){
            System.out.println("Welcome");
            return true;
        }else{
            System.out.println("Nope, you cannot come in");
            return false;
        }
    }
}
