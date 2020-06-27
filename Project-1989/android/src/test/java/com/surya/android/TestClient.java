package com.surya.android;

import java.util.Scanner;

public class TestClient {
    public static void main(String... args) {
        System.out.println("Command line client...");
        Scanner in = new Scanner(System.in);
        String line;
        while (true) {
            if (in.hasNextLine()) {
                line = in.nextLine();
                System.out.println(line);
            }
        }
    }
}
