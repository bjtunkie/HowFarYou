package com.chat.test.core;

import java.nio.ByteBuffer;

public class LargeInteger {

    private final ByteBuffer buf;
    private final byte[] arr;
    private final int size;

    public LargeInteger(byte[] bytes) {
        this(bytes.length);
        buf.put(bytes);
    }

    public LargeInteger(int numberOfBytes) {
        size = numberOfBytes;
        arr = new byte[size];
        buf = ByteBuffer.wrap(arr);
    }

    private int m1, m2;

    public void add(long value) {
        
    }
    public void increment() {
        for (int addr = size - 1; addr >= 0; addr--) {
            byte x = arr[addr];
            if (~x != 0) {

                for (m1 = 0, m2 = 7; m1 < 8; m1++, m2--) {
                    int bit = (x >> m1) & 1;
                    if (bit == 0) {

                        byte a = (byte) (1 << m1);
                        byte b = (byte) ((x >> m1) << m1);

                        x = (byte) (a | b);
                        break;
                    }
                }
                arr[addr] = x;
                while (++addr < size) {
                    arr[addr] = 0;
                }
                break;
            }

        }


    }

    public void decrement() {

    }

    @Override
    public String toString() {
        StringBuilder u = new StringBuilder();
        for (int x = 0; x < size; x++) {
            for (int i = 7; i >= 0; i--) {
                int bit = (arr[x] >> i & 1);
                u.append(bit);
            }
        }

        return u.toString();
    }
}
