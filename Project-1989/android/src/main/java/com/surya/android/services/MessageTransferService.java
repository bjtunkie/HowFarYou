package com.surya.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.chat.v1.network.*;
import com.chat.v1.network.utils.Util;
import com.surya.android.constants.URLS;

public class MessageTransferService extends Service {

    private TransferBinder that;

    private void startConn() {
        if (that == null) {
            that = new TransferBinder();
            that.setDefaultReference();
        }
        if (that.stagingArea == null) {
            that.stagingArea = new InternalStageArea(new ConnectionPool(), new WorkerThreadFactory(new CustomRequestPool()), new Util());
        }
        if (that.tcpServer == null) {
            that.tcpServer = TCPServer.instance;
        }

        that.start();
        that.makeConnections(URLS.DEFAULT_HOST, URLS.DEFAULT_PORT);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        startConn();
        return new TransferBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startConn();
        return super.onStartCommand(intent, flags, startId);
    }

    private static class TransferBinder extends MessageTransferBinder {

        private TCPServer tcpServer;
        private StagingArea stagingArea;

        private TransferBinder() {
        }

        @Override
        public StagingArea getNetInterface() {
            return stagingArea;
        }

        @Override
        public TCPServer getTCPServer() {
            return tcpServer;
        }

        @Override
        public void makeConnections(String host, int port) {
            tcpServer.makeConnectionWith(URLS.DEFAULT_HOST_ID, host, port);
        }

        private void start() {
            tcpServer.start(stagingArea);
        }
    }


}
