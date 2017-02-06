package com.li.utils.filetransfer;


import com.li.pro.bean.filetransfer.FileInfo;

/**
 * Create by Mingwei Li on 2017 2017-1-3 ����1:54:47
 */
public class SocketTransferConfig {
    private static SocketTransferConfig senderConfig;
    private String ip;
    private int port;
    private String hostName;

    public SocketTransferConfig setHostName(String hostName) {
        this.hostName = hostName;
        return senderConfig;
    }

    public String getHostName() {
        return hostName;
    }

    private FileInfo fileInfo;

    private SocketTransferConfig() {
    }

    public static SocketTransferConfig newInstance() {
        senderConfig = new SocketTransferConfig();
        return senderConfig;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public SocketTransferConfig setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
        return senderConfig;

    }

    public String getIp() {
        return ip;
    }

    public SocketTransferConfig setIp(String ip) {
        this.ip = ip;
        return senderConfig;
    }

    public int getPort() {
        return port;
    }

    public SocketTransferConfig setPort(int port) {
        this.port = port;
        return senderConfig;
    }

    @Override
    public String toString() {
        return "SocketTransferConfig{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", hostName='" + hostName + '\'' +
                ", fileInfo=" + fileInfo +
                '}';
    }
}
