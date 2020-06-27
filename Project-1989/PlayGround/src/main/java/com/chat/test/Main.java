package com.chat.test;

import com.chat.test.core.LargeInteger;
import com.chat.test.core.TempLargeInteger;

public class Main {

    public static void main(String... args) {
        int value = 1862402047; //0x6F01FFFF; //1862402047
        LargeInteger largeInteger = new LargeInteger(16);
        largeInteger.increment();
        largeInteger.increment();
        largeInteger.increment();
        p(value);

        System.out.println("The value: " + largeInteger.toString());
    }

    public static void p(int value) {
        System.out.println(value + " : " + Integer.toHexString(value) + " : " + Integer.toBinaryString(value));
    }

    public static void b(byte value) {
        System.out.println(value + " : " + Integer.toHexString(value) + " :" + Integer.toBinaryString(value));
    }
}
