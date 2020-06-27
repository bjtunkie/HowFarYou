package com.surya.android.services;

import android.os.Binder;
import com.chat.v1.network.StagingArea;
import com.chat.v1.network.TCPServer;

public abstract class MessageTransferBinder extends Binder {
    private static volatile MessageTransferBinder reference = null;

    public abstract StagingArea getNetInterface();

    public abstract TCPServer getTCPServer();

    public abstract void makeConnections(String host, int port);

    public final MessageTransferBinder setDefaultReference() {
        return reference = this;
    }

    public static MessageTransferBinder getDefaultBinder() {
        return reference;
    }
}
