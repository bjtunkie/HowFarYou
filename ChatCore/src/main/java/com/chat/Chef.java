package com.chat;

import com.chat.proto.ChefDeSerializer;
import com.chat.proto.ChefMap;
import com.chat.proto.ChefSerializer;

public abstract class Chef {
    protected final byte[] bytes = new byte[1024 * 1024 * 8]; // 8 mb of data


    protected final ChefSerializer serializer;
    protected final ChefDeSerializer deSerializer;

    Chef() {
        final ChefMap cache = new ChefMap();
        serializer = new ChefSerializer(cache);
        deSerializer = new ChefDeSerializer(cache);
    }

    protected abstract <T> int serialize(T input, byte[] output, int offset);

    protected abstract <T> byte[] serialize(T input, int offset);

    protected abstract <T> T deSerialize(byte[] array, Class<T> clazz, int offset);

    protected abstract <T> T deSerialize(byte[] array, Class<T> clazz, int offset, int length);


}