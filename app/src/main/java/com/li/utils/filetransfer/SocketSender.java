package com.li.utils.filetransfer;

import com.google.gson.Gson;
import com.li.pro.bean.filetransfer.FileInfo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Create by Mingwei Li on 2016 2016-12-16 ����1:41:41
 */
public class SocketSender extends BaseTransfer {
    private volatile static Socket socket;
    static BufferedOutputStream outputStream;
    private InputStream inputStream;
    private volatile static SocketSender socketO;
    private SocketTransferConfig senderConfig;
    private File file;
    //用于线程暂停
    private boolean suspend = false;
    private String control = ""; // 只是需要一个对象而已，这个对象没有实际意义

    public void setSuspend(boolean suspend) {
        if (!suspend) {
            synchronized (control) {
                control.notifyAll();
            }
        }
        this.suspend = suspend;
    }

    public boolean isSuspend() {
        return this.suspend;
    }

    private SocketSender(SocketTransferConfig senderConfig) {
        this.senderConfig = senderConfig;
    }

    public static synchronized SocketSender newInstance(
            SocketTransferConfig senderConfig) {
        socketO = new SocketSender(senderConfig);
        return socketO;
    }

    private void isPause() {
        synchronized (control) {
            if (suspend) {
                try {
                    control.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            isPause();
            init();
            isPause();
            parseHeader();
            isPause();
            parseBody();
            isPause();
            onSuccess();
        } catch (Exception e) {
            // TODO: handle exception
            onSenderListenner.onError(e);
        }
    }

    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        socket = new Socket(senderConfig.getIp(), senderConfig.getPort());
        outputStream = new BufferedOutputStream(socket.getOutputStream());

    }

    private FileInfo fileInfo;

    @Override
    public void parseHeader() throws Exception {

        // 解析header
        StringBuilder headerSb = new StringBuilder();
        fileInfo = new FileInfo(senderConfig.getFileInfo()
                .getFilePath());
        String jsonStr = new Gson().toJson(fileInfo) + "\n";
        // jsonStr = TYPE_FILE + SPERATOR + jsonStr ;
        headerSb.append(jsonStr);
        int leftLen = 10 * 1024 - jsonStr.getBytes("UTF-8").length;
        // 对于英文是一个字母对应一个字节，中文的情况下对应两个字节。剩余字节数不应该是字节数
        for (int i = 0; i < leftLen; i++) {
            headerSb.append(" ");
        }
        byte[] headbytes = headerSb.toString().getBytes("UTF-8");
        // 写入header
        outputStream.write(headbytes);
        outputStream.flush();
        if (null != onSenderListenner)
            onSenderListenner.onTransferStart(fileInfo);
    }

    @Override
    public void parseBody() throws Exception {
        if (null == socketO)
            throw new Exception("SocketO is null");
        if (null == senderConfig.getFileInfo().getFilePath()
                || "".equals(senderConfig.getFileInfo().getFilePath()))
            throw new Exception("paht is null");
        file = new File(senderConfig.getFileInfo().getFilePath());
        inputStream = new FileInputStream(file);

        if (null == inputStream)
            throw new Exception("InputStream is null");
        if (null == file)
            throw new Exception("File is not exist");
        byte[] b = new byte[4 * 1024];
        int lenth = 0;
        int currentSize = 0;
        while ((lenth = inputStream.read(b)) != -1) {
            isPause();
            outputStream.write(b, 0, lenth);
            currentSize += lenth;
            onSenderListenner.onProgress(fileInfo, senderConfig.getFileInfo().getSize(),
                    currentSize);
        }
        outputStream.flush();
        outputStream.close();
        socket.close();

    }

    @Override
    public void onSuccess() throws Exception {
        if (null == fileInfo) throw new Exception("FileInfo is null");
        onSenderListenner.onSuccess(fileInfo);
    }


}
