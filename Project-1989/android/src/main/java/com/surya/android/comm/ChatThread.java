package com.surya.android.comm;

import com.chat.v1.comm.ThreadPool;
import com.chat.v1.comm.ZThread;

public class ChatThread extends ZThread {
    public <T extends ZThread> ChatThread(int hash, ThreadPool<T> factory) {
        super(hash, factory);
    }

    @Override
    protected void work(byte[] bytes) {

    }
}
