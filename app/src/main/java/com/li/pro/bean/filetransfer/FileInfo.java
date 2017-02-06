package com.li.pro.bean.filetransfer;

import java.io.File;

/**
 * Create by Mingwei Li on 2017 2017-1-3 下午1:12:59
 */
public class FileInfo {
    private File file;
    private long date;
    private boolean directory;
    private String permisson;

    public void setDate(long date) {
        this.date = date;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public void setPermisson(String permisson) {
        this.permisson = permisson;
    }

    public long getDate() {

        return date;
    }

    public boolean isDirectory() {
        return directory;
    }

    public String getPermisson() {
        return permisson;
    }

    public FileInfo(String filePath) {
        file = new File(filePath);
        this.filePath = file.getAbsolutePath();
        this.size = file.length();
        this.fileType = file.getAbsolutePath().substring(
                file.getAbsolutePath().lastIndexOf(".") + 1);
        this.name = file.getAbsolutePath().substring(
                file.getAbsolutePath().lastIndexOf("/") + 1);
        this.date = file.lastModified();
        this.directory = file.isDirectory();
    }

    public FileInfo() {
    }

    /**
     * 文件传输的标识
     */
    // 1 成功 -1 失败
    public static final int FLAG_SUCCESS = 1;
    public static final int FLAG_DEFAULT = 0;
    public static final int FLAG_FAILURE = -1;

    // 必要属性
    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小
     */
    private long size;

    // 非必要属性
    /**
     * 文件显示名称
     */
    private String name;

    /**
     * 文件大小描述
     */
    private String sizeDesc;

    // /**
    // * 文件缩略图 (mp4与apk可能需要)
    // */
    // private Bitmap bitmap;

    /**
     * 文件额外信息
     */
    private String extra;

    /**
     * 已经处理的（读或者写）
     */
    private long procceed;

    /**
     * 文件传送的结果
     */
    private int result;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSizeDesc() {
        return sizeDesc;
    }

    public void setSizeDesc(String sizeDesc) {
        this.sizeDesc = sizeDesc;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getProcceed() {
        return procceed;
    }

    public void setProcceed(long procceed) {
        this.procceed = procceed;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "FileInfo [filePath=" + filePath + ", fileType=" + fileType
                + ", size=" + size + ", name=" + name + ", sizeDesc="
                + sizeDesc + ", extra=" + extra + ", procceed=" + procceed
                + ", result=" + result + "]";
    }

}
