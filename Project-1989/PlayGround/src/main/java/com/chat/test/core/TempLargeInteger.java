package com.chat.test.core;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class TempLargeInteger {
    private final Unsafe unsafe;
    private final byte[] buf;
    private final long address;
    private final int size;
    private long mark;

    public TempLargeInteger(int initVal) {
        this(4, initVal);
    }

    public TempLargeInteger(int numberOfBytes, int initVal) {
        Unsafe u = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            u = (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        unsafe = u;
        size = numberOfBytes;
        address = unsafe.allocateMemory(size);
        mark = 0;

        buf = new byte[size];
        unsafe.setMemory(address, size, (byte) 0);
        ByteBuffer xBuf = ByteBuffer.wrap(buf);
        xBuf.putInt(initVal);

    }

    private int m1, m2;
    /***
    public void increment() {
        long max = address + size;
        long addr = max - 1;
        for (; addr >= address; addr--) {
            byte x = unsafe.getByte(addr);
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
                unsafe.putByte(addr, x);
                break;
            }

        }


        while (++addr < max) {
            unsafe.putByte(addr, (byte) 0);
        }


        addr = address;
        for (int i = 0; addr < max; addr++, i++) {
            buf[i] = unsafe.getByte(addr);
        }

    }
    **/

    public void increment() {
        int addr = size - 1;
        for (; addr >= 0; addr--) {
            byte x = buf[addr];
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
                buf[addr] = x;
                break;
            }

        }

        while (++addr < size) {
            buf[addr] = 0;
        }

    }

    public void decrement() {

    }

    @Override
    public String toString() {
        StringBuilder u = new StringBuilder();
        for (int x = 0; x < 4; x++) {
            for (int i = 7; i >= 0; i--) {
               int bit =  (buf[x] >> i & 1);
               u.append(bit);
            }
            System.out.print(Integer.toBinaryString(buf[x]));
        }

        System.out.println();
        return u.toString();
    }
}
