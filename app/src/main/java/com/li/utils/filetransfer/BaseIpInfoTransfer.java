package com.li.utils.filetransfer;

import com.li.pro.bean.filetransfer.IpPortInfo;

/**
 * Created by Mingwei Li on 2017/1/11 0011.
 */

public abstract  class BaseIpInfoTransfer implements Runnable{
    public static OnSocketIpReceive onSocketIpReceive = null;

    public void setOnSocketIpReceiveListenner(OnSocketIpReceive onSocketIpReceive) {
        this.onSocketIpReceive = onSocketIpReceive;
    }
    public interface OnSocketIpReceive {
        void onSocketIpReceiveStart();

        void onSocketIpReceiveGetIp(IpPortInfo ip);

        void onSocketIpReceiveSuccess();

        void onSocketIpReceiveError(Exception e);
    }
}
