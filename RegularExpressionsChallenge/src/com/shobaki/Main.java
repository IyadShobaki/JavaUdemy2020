package com.shobaki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

	    String challenge1 = "I want a bike.";
        System.out.println(challenge1.matches("I want a bike."));

        System.out.println("***********************");

        //Match two strings beginning with "I want a"
        String challenge2a = "I want a bike.";
        String challenge2b = "I want a ball.";
        String regExp = "I want a \\w+.";
        System.out.println(challenge2a.matches(regExp));
        System.out.println(challenge2b.matches(regExp));
        //another solution by using group
        String regExp1 = "I want a (bike|ball).";
        System.out.println(challenge2a.matches(regExp1));
        System.out.println(challenge2b.matches(regExp1));

        //another solution using Matcher.matches() instead of String.matches()
        String regExp3 = "I want a \\w+.";
        Pattern pattern = Pattern.compile(regExp3);
        Matcher matcher = pattern.matcher(challenge2a);
        System.out.println(matcher.matches());
        matcher = pattern.matcher(challenge2b);
        System.out.println(matcher.matches());

        //Replace all blanks with underscore
        String challenge4 = "Replace all blanks with underscore.";
        System.out.println(challenge4.replaceAll(" ", "_"));
        System.out.println(challenge4.replaceAll("\\s","_"));

        //Reg ex will match the following string in its entirety
        String challenge5 = "aaabccccccccdddefffg";
        System.out.println(challenge5.matches("[abcdefg]+"));
        System.out.println(challenge5.matches("[a-g]+"));

        //Reg ex will only match the challenge 5 string in its entirety
        System.out.println(challenge5.matches("^a{3}bc{8}d{3}ef{3}g$"));
        System.out.println(challenge5.replaceAll("^a{3}bc{8}d{3}ef{3}g$", "REPLACED")); //to verify

        //Reg ex will match a string starts with a series of letters then . then digits

        String challenge7 = "abcd.135";
        System.out.println(challenge7.matches("^[A-z][a-z]+\\.\\d+$"));   //true
        //the carrot to ensure that nothing occur before the pattern.  d+ means one or more digits
        //the $ (dollar boundary matcher) to ensure that nothing occurs after this pattern

        //Modify Reg ex challenge 7 to use a group
        String challenge8 = "abcd.135uvqz.7tzik.999";
        String regExp8 = "[A-Za-z]+\\.(\\d+)";
        Pattern pattern8 = Pattern.compile(regExp8);
        Matcher matcher8 = pattern8.matcher(challenge8);

        while(matcher8.find()){
            System.out.println("Occurrence " + matcher8.group(1));
        }


        //
        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";
        String regExp9 = "[A-Za-z]+\\.(\\d+)\\s";
        Pattern pattern9 = Pattern.compile(regExp9);
        Matcher matcher9 = pattern9.matcher(challenge9);
        while(matcher9.find()){
            System.out.println("Occurrence " + matcher9.group(1));
        }

        //
        String challenge10 = "abcd.135\tuvqz.7\ttzik.999\n";
        String regExp10 = "[A-Za-z]+\\.(\\d+)\\s";
        Pattern pattern10 = Pattern.compile(regExp10);
        Matcher matcher10 = pattern10.matcher(challenge10);
        while(matcher10.find()){
            System.out.println("Occurrence: start = " + matcher10.start(1)+  " end = " + (matcher10.end(1)-1));
        }

        //Extract what's in the curly braces
        String challenge11 = "{0, 2}, {0, 5}, {1, 3}, {2, 4}";
        String regExp11 = "\\{(.+?)\\}";
        Pattern pattern11 = Pattern.compile(regExp11);
        Matcher matcher11 = pattern11.matcher(challenge11);
        while(matcher11.find()){
            System.out.println("Occurrence: " + matcher11.group(1));
        }

        System.out.println("***************************");
        String challenge11a = "{0, 2}, {0, 5}, {1, 3}, {2, 4}, {x, y}, {6, 34}, {11, 12}";
        String regExp11a = "\\{(\\d+, \\d+)\\}";
        //String regExp11a = "\\{(\\d+), (\\d+)\\}";
        Pattern pattern11a = Pattern.compile(regExp11a);
        Matcher matcher11a = pattern11a.matcher(challenge11a);
        while(matcher11a.find()){
            System.out.println("Occurrence: " + matcher11a.group(1));
        }

        //Reg ex match a 5 digit US zip code
//        String regExp12 = "^[0-9]{5}$";
        String regExp12 = "^\\d{5}$";  //teacher solution
        String challenge12 = "11111";
        System.out.println(challenge12.matches(regExp12));

        //Reg ex match a 5 digit zip code followed by - and another 4 digits
//        String regExp13 = "^\\d{5}[\\-]{1}\\d{4}$";
        String regExp13 = "^\\d{5}-\\d{4}$"; //teacher solution
        String challenge13 = "11111-1111";
        System.out.println(challenge13.matches(regExp13));

        System.out.println("****************************");
        //Reg ex accepted both entries from challenges 13 and 14
//        String regExp14 = "^\\d{5}$|^\\d{5}-\\d{4}$";
        String regExp14 = "^\\d{5}(-\\d{4})?$"; //teacher solution
        String challenge14a = "11111";
        System.out.println(challenge14a.matches(regExp14));
        String challenge14b = "11111-1111";
        System.out.println(challenge14b.matches(regExp14));

    }
}
