package com.li.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Mingwei Li on 2017/1/10 0010.
 */

public class IpUtils {
    private static volatile IpUtils ipUtils = null;

    private IpUtils() {
    }

    public static IpUtils getInstance() {
        if (null == ipUtils)
            ipUtils = new IpUtils();
        return ipUtils;
    }

    // 得到广播ip, 192.168.0.255之类的格式
    public String getBroadCastIP() {
        String ip = getLocalHostIp().substring(0,
                getLocalHostIp().lastIndexOf(".") + 1)
                + "255";
        return ip;
    }

    // 获取本机IP
    public String getLocalHostIp() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces();
            // 遍历所用的网络接口
            while (en.hasMoreElements()) {
                NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
                Enumeration<InetAddress> inet = nif.getInetAddresses();
                // 遍历每一个接口绑定的所有ip
                while (inet.hasMoreElements()) {
                    InetAddress ip = inet.nextElement();
                    if (!ip.isLoopbackAddress() && ip instanceof Inet4Address) {
                        return ip.getHostAddress();
                    }
                }

            }
        } catch (SocketException e) {
            System.out.print("获取IP失败");
            e.printStackTrace();
        }
        return null;
    }

    // 获取本机IP
    public InetAddress getLocalHostInetAddress() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces();
            // 遍历所用的网络接口
            while (en.hasMoreElements()) {
                NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
                Enumeration<InetAddress> inet = nif.getInetAddresses();
                // 遍历每一个接口绑定的所有ip
                while (inet.hasMoreElements()) {
                    InetAddress ip = inet.nextElement();
                    if (!ip.isLoopbackAddress() && ip instanceof Inet4Address) {
                        return ip;
                    }
                }
            }
        } catch (SocketException e) {
            System.out.print("获取IP失败");
            e.printStackTrace();
        }
        return null;
    }
}
