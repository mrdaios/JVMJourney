package org.mrdaios.chapter01;

public class InitializationTest {

    // init
    public int num1 = 1;
    // clinit
    public static int num2 = 2;
    // ConstatntValue
    public static final int num3 = 3;

    // init
    public String str1 = "str1";
    // clinit
    public static String str2 = "str2";
    // ConstatntValue
    public static final String str3 = "str3";
    // clinit
    public static final String str4 = new String("str4");

    // init
    public Integer integer1 = 1;
    // clinit
    public static Integer integer2 = 2;
    // clinit
    public static final Integer integer3 = 3;
    // clinit
    public static final Integer integer4 = Integer.valueOf(4);

}
