package com.chat.core.util;

import com.chat.proto.BaseMessage;
import com.chat.proto.MessageResponse;
import com.chat.proto.RequestPool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InternalRequestPool implements RequestPool {

    private final Map<String, MessageResponse> requests;
    private final Collection<Map.Entry<String, Long>> timestamps;
    private final Thread thread;

    public InternalRequestPool() {
        requests = new ConcurrentHashMap<>();
        timestamps = new ArrayList<>();
        thread = new Thread(this::analyze);
        thread.start();
    }

    private void analyze() {
        while (true) {

            long now = System.currentTimeMillis();
            Iterator<Map.Entry<String, Long>> it = timestamps.iterator();
            it.forEachRemaining(x -> {
                long duration = now - x.getValue();
                if (duration > TIME_TO_LIVE) {
                    String requestID = x.getKey();
                    it.remove();
                    requests.remove(requestID);
                }
            });

            try {
                Thread.sleep(TIME_TO_LIVE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public <T extends BaseMessage> void insert(T in, MessageResponse response) {
        String requestID = in.requestID();
        long ts = in.resetTimestamp();
        requests.put(requestID, response);
        timestamps.add(new AbstractMap.SimpleEntry<>(requestID, ts));

    }

    @Override
    public <T extends BaseMessage> MessageResponse extract(String key) {
        timestamps.remove(key);
        return requests.remove(key);
    }


}