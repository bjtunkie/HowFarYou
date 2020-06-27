package com.surya.android.comm;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.surya.android.log.LogFactory;
import com.surya.android.log.Logger;

public class TCPSocketConnectionHandler implements ServiceConnection {

    private static final TCPSocketConnectionHandler INSTANCE = new TCPSocketConnectionHandler();
    private final Logger Log = LogFactory.create(TCPSocketConnectionHandler.class);

    private TCPSocketConnectionHandler() {
    }

    public static TCPSocketConnectionHandler getInstance() {
        return INSTANCE;
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
