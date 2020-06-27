package com.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EasyChef extends Chef {
    private final Gson gson;

    public EasyChef() {
        super();
        gson = new GsonBuilder().create();
    }

    @Override
    public <T> byte[] serialize(T input, int offset) {
        String x = gson.toJson(input);
        byte[] bytes = x.getBytes();
        if (offset <= 0) {
            return bytes;
        }

        int size = bytes.length + offset;
        byte[] array = new byte[size];
        System.arraycopy(bytes, 0, array, offset, bytes.length);
        return array;
    }

    @Override
    public <T> int serialize(T input, byte[] output, int offset) {
        String x = gson.toJson(input);
        byte[] bytes = x.getBytes();
        System.arraycopy(bytes, 0, output, offset, bytes.length);
        return bytes.length + offset;
    }

    @Override
    public <T> T deSerialize(byte[] array, Class<T> clazz, int offset) {
        return deSerialize(array, clazz, offset, array.length - offset);
    }

    @Override
    public <T> T deSerialize(byte[] array, Class<T> clazz, int offset, int length) {
        String json = new String(array, offset, length);
        return gson.fromJson(json, clazz);
    }

}
