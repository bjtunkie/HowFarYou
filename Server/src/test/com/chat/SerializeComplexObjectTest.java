package com.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.chat.sampleobjects.ComplexObject;

import java.nio.ByteBuffer;

public class SerializeComplexObjectTest {
    public static void main(String... args) {


        ComplexObject o = new ComplexObject();
        o.add("surya");


        Chef chef = new DefaultChef();
        byte[] bytes = chef.serialize(o, 0);

        ComplexObject object = chef.deSerialize(bytes, ComplexObject.class,0);


        ByteBuffer buf = ByteBuffer.wrap(bytes);
        for (int i = 0, size = bytes.length; i < size; i++) {
            System.out.print(buf.get() + " ");
        }


        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        String data = gson.toJson(object);
        System.out.println(data);


    }

}
