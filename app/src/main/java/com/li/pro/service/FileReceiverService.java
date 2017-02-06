package com.li.pro.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Mingwei Li on 2017/1/6 0006.
 */

public class FileReceiverService extends Service {
    public static final String ACTION_STRART_FILERECEIVER = "com.li.pro.broadcastreceiver.ReceiveFileRecever";

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent();
        intent.setAction(ACTION_STRART_FILERECEIVER);
        getApplicationContext().sendBroadcast(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
