package com.li.pro.bean.filetransfer;

import java.io.Serializable;

/**
 * Ip Port 实体
 * Created by mayubao on 2016/11/30.
 * Contact me 345269374@qq.com
 */
public class IpPortInfo implements Serializable {
    private String ipAddress;
    private String hostName;
    private int sendPort;
    private int receivePort;

    @Override
    public String toString() {
        return "IpPortInfo{" +
                "ipAddress='" + ipAddress + '\'' +
                ", hostName='" + hostName + '\'' +
                ", sendPort=" + sendPort +
                ", receivePort=" + receivePort +
                '}';
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setSendPort(int sendPort) {
        this.sendPort = sendPort;
    }

    public void setReceivePort(int receivePort) {
        this.receivePort = receivePort;
    }

    public String getIpAddress() {

        return ipAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public int getSendPort() {
        return sendPort;
    }

    public int getReceivePort() {
        return receivePort;
    }
}
