package com.shobaki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
//	    String string = "I am a string. Yes, I am.";
//        System.out.println(string);
//
//        String yourString = string.replaceAll("I", "You");
//        System.out.println(yourString);
//
//        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
//        System.out.println(alphanumeric.replaceAll(".","Y"));
//        //will replace all with YYYYYYYYYYYYYYYYYYYYYYY
//
//        System.out.println(alphanumeric.replaceAll("^abcDeee", "YYY"));
//        //YYYF12Ghhiiiijkl99z
//
//        String secondString = "abcDeeeF12GhhabcDeeeiiiijkl99z";
//        System.out.println(secondString.replaceAll("^abcDeee", "YYY"));
//        //YYYF12GhhabcDeeeiiiijkl99z
//
//        System.out.println(alphanumeric.matches("^hello"));  //return false
//        System.out.println(alphanumeric.matches("^abcDeee"));//return false too
//        System.out.println(alphanumeric.matches("^abcDeeeF12Ghhiiiijkl99z"));//true
//        System.out.println(alphanumeric.matches("abcDeeeF12Ghhiiiijkl99z"));//true
//
//        System.out.println(alphanumeric.replaceAll("ijkl99z$", "THE END"));
//
//        System.out.println(alphanumeric.replaceAll("[aei]", "X"));
//        //XbcDXXXF12GhhXXXXjkl99z
//        System.out.println(alphanumeric.replaceAll("[aei]", "I replaced a letter here"));
//
//        //aei will replace with X just if anyone of them followed by F or j
//        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));
//        //abcDeeX12GhhiiiXkl99z
//
//        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));
//
//        String newAlphanumeric = "abcDeeeF12Ghhiiiijkl99z";
//        System.out.println(newAlphanumeric.replaceAll("[^ej]", "X"));//replace all except e and j
//        //XXXXeeeXXXXXXXXXXjXXXXX
//        System.out.println(newAlphanumeric.replaceAll("[abcdef345678]","X"));
//        //XXXDXXXF12Ghhiiiijkl99z
//        System.out.println(newAlphanumeric.replaceAll("[a-f3-8]","X"));
//        //XXXDXXXF12Ghhiiiijkl99z
//        System.out.println(newAlphanumeric.replaceAll("[a-fA-F3-8]","X")); //  =  "(?i)[a-f3-8]"
//        //XXXXXXXX12Ghhiiiijkl99z
//
//        System.out.println(newAlphanumeric.replaceAll("(?i)[a-f3-8]","X"));
//        //XXXXXXXX12Ghhiiiijkl99z
//
//        System.out.println(newAlphanumeric.replaceAll("[0-9]","X")); // = "\\d"
//        //abcDeeeFXXGhhiiiijklXXz
//        System.out.println(newAlphanumeric.replaceAll("\\d","X"));
//        //abcDeeeFXXGhhiiiijklXXz
//
//        System.out.println(newAlphanumeric.replaceAll("\\D","X"));
//        //XXXXXXXX12XXXXXXXXXX99X
//
//        String hasWhiteSpace = "I have blanks and\ta tab, and also a newline\n";
//        System.out.println(hasWhiteSpace);
//        //remove all white space
//        System.out.println(hasWhiteSpace.replaceAll("\\s",""));
//        //Ihaveblanksandatab,andalsoanewline
//
//        System.out.println(hasWhiteSpace.replaceAll("\t","X"));
//        //I have blanks andXa tab, and also a newline
//        System.out.println(hasWhiteSpace.replaceAll("\\S","X"));
//        //will remove all non white space characters. Only Tab, Spaces & newline characters remain
//
//        System.out.println(newAlphanumeric.replaceAll("\\w","X"));
//        //XXXXXXXXXXXXXXXXXXXXXXX
//        System.out.println(hasWhiteSpace.replaceAll("\\w","X"));
//        //X XXXX XXXXXX XXX	X XXX, XXX XXXX X XXXXXXX
//
//        System.out.println(hasWhiteSpace.replaceAll("\\b","X"));
//        //XIX XhaveX XblanksX XandX	XaX XtabX, XandX XalsoX XaX XnewlineX
//
//        System.out.println(hasWhiteSpace.replaceAll("\\W","X"));
//        //IXhaveXblanksXandXaXtabXXandXalsoXaXnewlineX

        ///----------------------------------------------------------------

//        String thirdAlphanumeric = "abcDeeeF12Ghhiiiijkl99z";
//        System.out.println(thirdAlphanumeric.replaceAll("^abcDeee", "YYY"));
//       // YYYF12Ghhiiiijkl99z
//        System.out.println(thirdAlphanumeric.replaceAll("^abcDe{3}", "YYY"));    //using quantifiers
//        //YYYF12Ghhiiiijkl99z     same result as above
//        System.out.println(thirdAlphanumeric.replaceAll("^abcDe+", "YYY"));
//        //YYYF12Ghhiiiijkl99z     same result as above
//        System.out.println(thirdAlphanumeric.replaceAll("^abcDe*", "YYY"));
//        //YYYF12Ghhiiiijkl99z     same result as above
//        System.out.println(thirdAlphanumeric.replaceAll("^abcDe{2,5}", "YYY")); //e should be 2 more until 5
//        //YYYF12Ghhiiiijkl99z
//        System.out.println(thirdAlphanumeric.replaceAll("h+i*j", "Y"));
//        //abcDeeeF12GYkl99z
//
//        System.out.println("****************************");
//        //after removing the eee
//        String forthAlphanumeric = "abcDF12Ghhiiiijkl99z";
//        System.out.println(forthAlphanumeric.replaceAll("^abcDeee", "YYY"));
//        // abcDF12Ghhiiiijkl99z
//        System.out.println(forthAlphanumeric.replaceAll("^abcDe{3}", "YYY"));    //using quantifiers
//        //abcDF12Ghhiiiijkl99z   same result as above
//        System.out.println(forthAlphanumeric.replaceAll("^abcDe+", "YYY"));
//        //abcDF12Ghhiiiijkl99z     same result as above
//        System.out.println(forthAlphanumeric.replaceAll("^abcDe*", "YYY"));
//        //YYYF12Ghhiiiijkl99z

        //------------------------------------------

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");

        // 1
////        String h2Pattern ="<h2>";  //will return false
//        String h2Pattern =".*<h2>.*";  //will return true
//        Pattern pattern = Pattern.compile(h2Pattern);//, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
//        Matcher matcher = pattern.matcher(htmlText);
//        System.out.println(matcher.matches());


        // 2
        String h2Pattern ="<h2>";  // we have to use this with the loop and not .*<h2>.*
        Pattern pattern = Pattern.compile(h2Pattern);//, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());
        //we have to reset the matcher before we can use it again
        matcher.reset();
        int count =0;
        while(matcher.find()){
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());
        }

        System.out.println("*************************************");
        // 3 using group
//        String h2GroupPattern = "(<h2>)";
//        String h2GroupPattern = "(<h2>.*</h2>)";
//        String h2GroupPattern = "(<h2>.+?</h2>)";
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while(groupMatcher.find()){
            System.out.println("Occurrence " + groupMatcher.group(1));
        }


        String h2TextGroups  = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while(h2TextMatcher.find()){
            System.out.println("Occurrence " + h2TextMatcher.group(2));
            //group(2) means (.+?) and will be the text between group(1)-<h2> and group(3)-</h2>
        }

        // 4 using And , Or & Not
        //"abc" = "a" and "b" and "c"
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));

        //[^abc] carrot character here means not (match all characters except abc)
        String tvTest = "tstvtkt";
//        String tNotVRegExp = "t[^v]"; //2 cases and the last t will not consider
//        String tNotVRegExp = "t(?!v)"; // (negative look a head) we want all the t that not followed with v (in this case there are 3 )
        String tNotVRegExp = "t(?=v)"; // (positive look a head) and its one case tv but not include the v in the match
        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        count = 0;
        while(tNotVMatcher.find()){
            count++;
            System.out.println("Occurrence " + count + " : " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
        }

        // Validate a US phone number RegEx
        //^([\(]{1}[0-9]{3}[\)]{1}[ ]{1}[0-9]{3}[\-]{1}[0-9]{4})$
        String phone1 = "1234567890";  //shouldn't match
        String phone2 = "(123) 456-7890"; //match
        String phone3 = "123 456-7890"; //shouldn't match
        String phone4 = "(123)456-7890";//shouldn't match

        System.out.println("phone1 = " + phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone2 = " + phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone3 = " + phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone4 = " + phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));

        //Validate a visa number
        //^4[0-9]{12}([0-9]{3})?$
        String visa1 = "4444444444444"; //match
        String visa2 = "5444444444444"; //shouldn't match
        String visa3 = "4444444444444444"; //match
        String visa4 = "4444"; //shouldn't match

        System.out.println("visa1 " + visa1.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa2 " + visa2.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa3 " + visa3.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa4 " + visa4.matches("^4[0-9]{12}([0-9]{3})?$"));


    }
}
