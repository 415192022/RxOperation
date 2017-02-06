package com.li.utils.filetransfer;

import com.google.gson.Gson;
import com.li.pro.bean.filetransfer.FileInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create by Mingwei Li on 2017 2017-1-3 ����1:22:19
 */
public class SocketReceiver extends BaseTransfer {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static InputStream inputStream;
    private static FileInfo fileInfo;
    private static volatile SocketReceiver socketReceiver;
    private static SocketTransferConfig socketTransferConfig;
    //用于线程暂停
    private boolean suspend = false;
    private String control = ""; // 只是需要一个对象而已，这个对象没有实际意义

    private void closeSocket() {
        try {
            if (null != socket) {
                socket.close();
                socket = null;
            }
        } catch (Exception e) {
        }

    }

    private void closeServerSocket() {
        try {
            if (null != serverSocket) {
                serverSocket.close();
                serverSocket = null;
            }
        } catch (Exception e) {
        }
    }

    public void destroy() {
        closeSocket();
        closeServerSocket();
    }

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

    private Thread currentThread = null;

    public Thread getCurrentThread() {
        return currentThread;
    }

    private SocketReceiver(SocketTransferConfig socketTransferConfig) {
        this.socketTransferConfig = socketTransferConfig;
        try {
            serverSocket = new ServerSocket(socketTransferConfig.getPort());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static synchronized SocketReceiver newInstance(
            SocketTransferConfig senderConfig) {
        socketReceiver = new SocketReceiver(senderConfig);
        return socketReceiver;
    }

    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("接收文件准备就绪!!!");
        socket = serverSocket.accept();
        System.out.print("===============" + socket.getInetAddress().getHostAddress());
        inputStream = socket.getInputStream();
    }

    @Override
    public void parseHeader() throws Exception {
        // 解析Header
        byte[] headerBytes = new byte[10 * 1024];
        int headTotal = 0;
        int readByte = -1;
        while ((readByte = inputStream.read()) != -1) {
            headerBytes[headTotal] = (byte) readByte;
            headTotal++;
            if (headTotal == headerBytes.length) {
                break;
            }
        }
        System.out.print("mmmmmmmmmmmmmmmmmm " + new String(headerBytes, "UTF-8"));
        fileInfo = new Gson().fromJson(new String(headerBytes, "UTF-8"),
                FileInfo.class);
        String savePath = socketTransferConfig.getFileInfo()
                .getFilePath() + "/" + fileInfo.getName();
        fileInfo.setFilePath(savePath);
        bos = new FileOutputStream(savePath.trim());
        System.out.println("===============" + savePath);
        if (null != onSenderListenner)
            onSenderListenner.onTransferStart(fileInfo);
    }

    private OutputStream bos;

    @Override
    public void parseBody() throws Exception {
        // TODO Auto-generated method stub
        // 解析Body
        byte[] bytes = new byte[10 * 1024];
        long total = 0;
        int len = 0;

        while ((len = inputStream.read(bytes)) != -1) {
            isPause();
            bos.write(bytes, 0, len);
            total = total + len;
            if (null != onSenderListenner)
                onSenderListenner.onProgress(fileInfo, fileInfo.getSize(), total);

        }

    }

    @Override
    public void onSuccess() throws Exception {
        // TODO Auto-generated method stub
        bos.flush();
        bos.close();
        inputStream.close();
        if (null != onSenderListenner)
            onSenderListenner.onSuccess(fileInfo);
    }

    @Override
    public void run() {
        try {
            currentThread = Thread.currentThread();
            if (!Thread.currentThread().isInterrupted()) {
                isPause();
                init();
                isPause();
                parseHeader();
                isPause();
                parseBody();
                isPause();
                onSuccess();
            } else {
                return;
            }
        } catch (Exception e) {
            if (null != onSenderListenner)
                onSenderListenner.onError(e);
        }
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
}
