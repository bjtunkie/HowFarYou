package com.chat;


import com.chat.sampleobjects.SimpleObject;

import java.nio.ByteBuffer;

public class SerializeOntoTest {

    public static void main(String... args) {
        System.out.println("Start-");
        test1();
        System.out.println("\nFinished.");
    }

    public static void test1() {
        SimpleObject simpleObject = new SimpleObject();


//        simpleObject.simpleObject = new SimpleObject();
//        simpleObject.simpleObject.set((byte) 11);
        Chef chef = new DefaultChef();
        byte[] data = chef.serialize(simpleObject, 0);

        ByteBuffer buf = ByteBuffer.wrap(data);
        System.out.println(buf.get());
        System.out.println(buf.get());
    }
}