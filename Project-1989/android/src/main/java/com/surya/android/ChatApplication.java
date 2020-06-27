package com.surya.android;

import android.app.Application;
import android.content.Intent;
import com.surya.android.comm.TCPSocketConnectionHandler;
import com.surya.android.services.MessageTransferService;

public class ChatApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Intent intent = new Intent(this, MessageTransferService.class);
        bindService(intent, TCPSocketConnectionHandler.getInstance(), BIND_AUTO_CREATE);
    }

}
