package com.chat.sampleobjects;

public class TestVars {

    public int[] intArray = new int[]{5, 8, 4, 1, 6, 3};


    public <T> void printArray(T[] arr) {
        System.out.println();
        for (T i : arr) {
            System.out.print(" " + i);
        }
        System.out.println();
    }
}
