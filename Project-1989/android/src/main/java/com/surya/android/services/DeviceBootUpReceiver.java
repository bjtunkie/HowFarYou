package com.surya.android.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceBootUpReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent incomingDataServiceStartIntent = new Intent(context, MessageTransferService.class);
        context.startService(incomingDataServiceStartIntent);

    }
}
