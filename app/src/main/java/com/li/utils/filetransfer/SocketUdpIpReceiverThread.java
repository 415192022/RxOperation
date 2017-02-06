package com.li.utils.filetransfer;

import com.google.gson.Gson;
import com.li.pro.bean.filetransfer.IpPortInfo;
import com.li.utils.IpUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;

/**
 * Created by Mingwei Li on 2017/1/6 0006.
 */

public class SocketUdpIpReceiverThread extends BaseIpInfoTransfer {
    private DatagramSocket ds;

    public static SocketUdpIpReceiverThread newInstance() {
        return new SocketUdpIpReceiverThread();
    }

    private CustomBlockingThreadPoolExecutor customBlockingThreadPoolExecutor;
    private ExecutorService executorService;

    private SocketUdpIpReceiverThread() {
        customBlockingThreadPoolExecutor = new CustomBlockingThreadPoolExecutor();
        customBlockingThreadPoolExecutor.init();
        executorService = customBlockingThreadPoolExecutor.getCustomThreadPoolExecutor();
    }

    public void destroy() {
        if (null != ds) {
            ds.close();
        }
    }

    private Thread currentThread = null;

    public Thread getCurrentThread() {
        return currentThread;
    }

    @Override
    public void run() {
        currentThread = Thread.currentThread();
        startIpReceive();
    }


    void startIpReceive() {
        System.out.println("开始准备接收IP");
        if (null != onSocketIpReceive) {
            onSocketIpReceive.onSocketIpReceiveStart();
        }
        currentThread = Thread.currentThread();
        try {
            while (true) {// 1、创建DatagramSocket;
                if (currentThread.isInterrupted()) {
                    return;
                }
                ds = new DatagramSocket(2425);
                // 2、创建数据包，用于接收内容。
                byte[] data = new byte[1024 * 40];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                // 3、接收数据
                packet.setData(data);
                System.out.println("准备接收IP");
                ds.receive(packet);
                System.out.println("接受到了");
                byte[] data2 = new byte[packet.getLength()];
                System.arraycopy(data, 0, data2, 0, data2.length);// 得到接收的数据
                String targetIP = new String(data2, "UTF-8");
                System.out.println("接收到IP" + targetIP);
                IpPortInfo ipPortInfo = new Gson().fromJson(targetIP, IpPortInfo.class);
                if (null != onSocketIpReceive) {
                    onSocketIpReceive.onSocketIpReceiveGetIp(ipPortInfo);
                }
                System.out.println("接收IP完成");
                System.out.println(IpUtils.getInstance().getLocalHostIp().trim() + "======>" + ipPortInfo.getIpAddress().trim()
                        + "   <======" + IpUtils.getInstance().getLocalHostIp().trim().equals(ipPortInfo.getIpAddress().trim()));
                //加此判断条件是为了防止自己发送IP广播接收到后造成的死循环
                if (!IpUtils.getInstance().getLocalHostIp().trim().equals(ipPortInfo.getIpAddress().trim())) {
                    System.out.println("发送给接收到的IP主机 " + ipPortInfo.getIpAddress() + "一个本机的IP地址");
                    executorService.execute(SocketTcpIpSenderThread.
                            newInstance(
                                    SocketTransferConfig.
                                            newInstance().
                                            setPort(9997).
                                            setIp(ipPortInfo.getIpAddress().trim())
                            ));
                }
                // Msg msg = (Msg) Tools.toObject(data2);
                ds.close();
                if (null != onSocketIpReceive) {
                    onSocketIpReceive.onSocketIpReceiveSuccess();
                }
            }

        } catch (Exception e) {
            if (null != onSocketIpReceive) {
                onSocketIpReceive.onSocketIpReceiveError(e);
            }
        }
    }
}
