package com.li.utils.filetransfer;

import com.li.pro.bean.filetransfer.FileInfo;

/**
 * Create by Mingwei Li on 2017 2017-1-4 ����11:08:52
 */
public abstract class BaseTransfer implements ITransfer, Runnable {
    public static final int BYTE_SIZE_HEADER = 1024 * 10;
    public static final int BYTE_SIZE_SCREENSHOT = 1024 * 40;
    public static final int BYTE_SIZE_DATA = 1024 * 4;

    public interface OnSenderListenner {
        void onTransferStart(FileInfo fileInfo);

        void onProgress(FileInfo fileInfo, long totleSize, long currentSize);

        void onSuccess(FileInfo fileInfo);

        void onError(Exception e);
    }

    protected OnSenderListenner onSenderListenner;

    public void setOnSenderListenner(OnSenderListenner onSenderListenner) {
        this.onSenderListenner = onSenderListenner;
    }
}
