package com.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.chat.sampleobjects.ModerateObject;

import java.nio.ByteBuffer;

public class SerializeModerateObjectTest {
    public static void main(String... args) {


        Object o = new ModerateObject(15);
        ((ModerateObject) o).setM(new ModerateObject(45));


        Chef chef = new DefaultChef();
        byte[] bytes = chef.serialize(o,0);

        ModerateObject object = chef.deSerialize(bytes, ModerateObject.class,0);


        ByteBuffer buf = ByteBuffer.wrap(bytes);
        for (int i = 0, size = bytes.length; i < size; i++) {
            System.out.print(buf.get() + " ");
        }


        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        String data = gson.toJson(object);
        System.out.println(data);


    }

}
