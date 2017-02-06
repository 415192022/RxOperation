package com.li.utils.filetransfer;

import com.google.gson.Gson;
import com.li.pro.bean.filetransfer.IpPortInfo;
import com.li.utils.IpUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Mingwei Li on 2017/1/6 0006.
 */

public class SocketUdpIpSenderThread extends BaseIpInfoTransfer  {
    public static SocketUdpIpSenderThread newInstance() {
        return new SocketUdpIpSenderThread();
    }

    @Override
    public void run() {
        startSender();
    }

    void startSender() {
        try {
            IpPortInfo ipPortInfo = new IpPortInfo();
            InetAddress inetAddress = IpUtils.getInstance().getLocalHostInetAddress();
            ipPortInfo.setIpAddress(inetAddress.getHostAddress());
            ipPortInfo.setHostName(inetAddress.getHostName());
            ipPortInfo.setSendPort(2426);
            ipPortInfo.setReceivePort(2425);
            String json = new Gson().toJson(ipPortInfo);
            System.out.println("发送本机IP: " + json);
            byte[] data = json.getBytes();
            System.out.println("发送本机IP Bytes: " + data);
            // 1、创建DatagramSocket用于UDP数据传送
            DatagramSocket ds = new DatagramSocket(2426);
            // 2、创建需要发送的数据包
            DatagramPacket packet = new DatagramPacket(data, data.length,
                    InetAddress.getByName(IpUtils.getInstance().getBroadCastIP()), 2425);
            // 3、发送
            packet.setData(data);
            ds.send(packet);
            // 4、关闭连接
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
