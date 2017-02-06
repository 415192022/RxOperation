package com.li.utils.filetransfer;

import com.google.gson.Gson;
import com.li.pro.bean.filetransfer.IpPortInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mingwei Li on 2017/1/12 0012.
 */

public class SocketTcpIpReceiveThread implements Runnable {
    private Thread thread = null;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static InputStream inputStream;


    public static synchronized SocketTcpIpReceiveThread newInstance() {
        return new SocketTcpIpReceiveThread();
    }

    private SocketTcpIpReceiveThread() {
        try {
            serverSocket = new ServerSocket(9997);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        thread = Thread.currentThread();
        startTcpIpReceive();
    }

    public void destroy() {
        try {
            if (null != socket && !socket.isClosed()) {
                socket.close();
            }
            if (null != serverSocket && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            if (null != thread) {
                thread.interrupt();
            }
            socket = null;
            serverSocket = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Thread getCurrentThread() {
        return thread;
    }

    void startTcpIpReceive() {
        try {
            while (true) {
                try {
                    System.out.println("TCP接收IP准备就绪!!!");
                    socket = serverSocket.accept();
                    System.out.println("===============" + socket.getInetAddress().getHostAddress());
                    inputStream = socket.getInputStream();
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
                    String json = new String(headerBytes, "UTF-8");
                    System.out.println("mmmkkkkmmmmmmmmmmmmmmm " + json);
                    IpPortInfo ipPortInfo = new Gson().fromJson(json, IpPortInfo.class);
                    System.out.println("mmmkkkkmmmmmmmmmmmmmmm " + onTcpIpReceiveConfirmListenner);
                    if (null != onTcpIpReceiveConfirmListenner) {
                        onTcpIpReceiveConfirmListenner.OnTcpIpReceiveConfirm(ipPortInfo);
                    }
                    System.out.println("TCP接收到对方IP" + ipPortInfo);
                    //接收到对方IP后 再向此IP发送本机IP
                    inputStream.close();
                    socket.close();
                    serverSocket.close();
                    inputStream = null;
                    socket = null;
                    serverSocket = null;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {

        }
    }

    private OnTcpIpReceiveConfirmListenner onTcpIpReceiveConfirmListenner = null;

    public interface OnTcpIpReceiveConfirmListenner {
        void OnTcpIpReceiveConfirm(IpPortInfo localInfo);
    }

    public void setOnTcpIpReceiveConfirmListenner(OnTcpIpReceiveConfirmListenner onTcpIpReceiveConfirmListenner) {
        this.onTcpIpReceiveConfirmListenner = onTcpIpReceiveConfirmListenner;
    }
}
