package com.shobaki;

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

        String thirdAlphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(thirdAlphanumeric.replaceAll("^abcDeee", "YYY"));

    }
}
