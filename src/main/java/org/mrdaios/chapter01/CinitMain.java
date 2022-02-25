package org.mrdaios.chapter01;

public class CinitMain {
    public static String a;

    {
        a = "123";
        System.out.println("clinit");
    }

    public static void main(String[] args) {
        new CinitMain();
    }
}
