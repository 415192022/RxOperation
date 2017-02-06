package com.li.utils.filetransfer;

import com.google.gson.Gson;
import com.li.pro.bean.filetransfer.IpPortInfo;
import com.li.utils.IpUtils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Mingwei Li on 2017/1/11 0011.
 */

public class SocketTcpIpSenderThread extends BaseIpInfoTransfer {
    private volatile static Socket socket;
    static BufferedOutputStream outputStream;
    private static SocketTcpIpSenderThread socketTcpIpSenderThread;
    private SocketTransferConfig socketTransferConfig;

    private SocketTcpIpSenderThread(SocketTransferConfig socketTransferConfig) throws IOException {
        this.socketTransferConfig = socketTransferConfig;
        socket = new Socket(socketTransferConfig.getIp(), socketTransferConfig.getPort());
        outputStream = new BufferedOutputStream(socket.getOutputStream());
    }

    public static SocketTcpIpSenderThread newInstance(SocketTransferConfig socketTransferConfig) {
        try {
            socketTcpIpSenderThread = new SocketTcpIpSenderThread(socketTransferConfig);
        } catch (IOException e) {
            return null;
        }
        return socketTcpIpSenderThread;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            StringBuilder headerSb = new StringBuilder();
            IpPortInfo localInfo = new IpPortInfo();
            InetAddress inetAddress = IpUtils.getInstance().getLocalHostInetAddress();
            localInfo.setIpAddress(inetAddress.getHostAddress());
            localInfo.setHostName(inetAddress.getHostName());
            localInfo.setSendPort(2426);
            localInfo.setReceivePort(2425);
            String jsonStr = new Gson().toJson(localInfo) + "\n";
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
            outputStream.close();
            socket.close();
            System.out.println("向对方 IP: " + socketTransferConfig.getIp() + "   端口: " + socketTransferConfig.getPort() + "发送成功" + new String(headbytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
