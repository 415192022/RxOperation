package com.li.pro.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.li.pro.service.FileReceiverService;

/**
 * Created by Mingwei Li on 2017/1/6 0006.
 */

public class ReceiveFileRecever extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(FileReceiverService.ACTION_STRART_FILERECEIVER)){
            if(null != onFileReceiverServiceListenner){
                onFileReceiverServiceListenner.onStartReceiver();
            }
        }
    }
    private OnFileReceiverServiceListenner onFileReceiverServiceListenner;

    public interface OnFileReceiverServiceListenner {
        void onStartReceiver();
    }
    public void setOnFileReceiverServiceListenner(OnFileReceiverServiceListenner onFileReceiverServiceListenner) {
        this.onFileReceiverServiceListenner = onFileReceiverServiceListenner;
    }
}
