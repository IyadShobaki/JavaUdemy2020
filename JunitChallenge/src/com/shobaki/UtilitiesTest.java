package com.shobaki;


import static org.junit.Assert.*;

public class UtilitiesTest {

    private Utilities util;
    @org.junit.Before
    public void setup() {
        util = new Utilities();
        System.out.println("Running a test...");
    }

    @org.junit.Test
    public void everyNthChar() {
      //  fail("This test has not been implemented");
        //Utilities util = new Utilities();
        char[] output = util.everyNthChar(new char[] {'h','e','l','l','o'}, 2);
        assertArrayEquals(new char[]{'e','l'}, output);
        char[] output2 = util.everyNthChar(new char[] {'h','e','l','l','o'}, 8);
        assertArrayEquals(new char[]{'h','e','l','l','o'}, output2);

    }

    @org.junit.Test
    public void removePairs() {
      //Utilities util = new Utilities();
      assertEquals("ABCDEF", util.removePairs("AABCDDEEF"));
      assertEquals("ABCABDEF", util.removePairs("ABCCABDEEF"));
      assertNull("Did not get null returned when argument passed was null", util.removePairs(null));
      assertEquals("A", util.removePairs("A"));
      assertEquals("", util.removePairs(""));
    }

    @org.junit.Test
    public void converter() {
        //fail("This test has not been implemented");
        //Utilities util = new Utilities();
        assertEquals(300,util.converter(10,5));

    }
    @org.junit.Test(expected = ArithmeticException.class)
    public void converter_arithmeticException(){
        Utilities util = new Utilities();
        util.converter(10,0);
    }

    @org.junit.Test
    public void nullIfOddLength() {
      //  fail("This test has not been implemented");
        //Utilities util = new Utilities();
        assertNull(util.nullIfOddLength("odd"));
        assertNotNull(util.nullIfOddLength("even"));

//        String test = util.nullIfOddLength("Door");
//        assertEquals("Door", test);
//        String test2= util.nullIfOddLength("Doors");
//        assertNull("Did not get null returned when argument passed was null", test2);
    }
}