package com.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.chat.sampleobjects.SimpleObject;

import java.nio.ByteBuffer;

public class SerializeSimpleObjectTest {
    public static void main(String... args) {


        SimpleObject o = new SimpleObject();


        Chef chef = new DefaultChef();
        byte[] bytes = chef.serialize(o, 0);
        SimpleObject object = chef.deSerialize(bytes, SimpleObject.class, 0);

        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        String data = gson.toJson(object);
        System.out.println(data);


        ByteBuffer buf = ByteBuffer.wrap(bytes);
        for (int i = 0; i < 7; i++) {
            System.out.print(buf.getInt() + " .... ");
        }

    }

}
