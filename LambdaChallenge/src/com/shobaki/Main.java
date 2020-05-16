package com.shobaki;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";
                String[] parts = myString.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };
        //lambda expression
        Runnable runnable1 = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };



        //lambda expression
        Function<String, String> lambdaFunction =  source -> {  //this method is equivalent to everySecondChar()
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        System.out.println(lambdaFunction.apply("1234567890"));
        System.out.println(everySecondChar("1234567890"));

        String result = everySecondCharacter(lambdaFunction, "1234567890");
        System.out.println(result);
     //   System.out.println(everySecondCharacter(lambdaFunction, "1234567890"));

       // Supplier<String> iLoveJave = ()->"I Love JAve!"; //we don't have to include the return keyword
        Supplier<String> iLoveJave = ()->{  //if we want to add return keyword
            return "I Love Jave!"; };

        String supplierResult = iLoveJave.get();
        System.out.println(supplierResult);


    }
    public static String everySecondChar(String source) {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    public static String everySecondCharacter(Function<String, String> func, String source){
        return func.apply(source);
    }

}

