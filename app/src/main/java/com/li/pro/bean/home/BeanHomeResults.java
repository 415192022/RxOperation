package com.li.pro.bean.home;

import java.util.List;

/**
 * Created by Mingwei Li on 2016/11/11 0011.
 * 主页
 */

public class BeanHomeResults extends BeanHomeBase {
    private String _id;
    private String createdAt;
    private String desc;
    private List<String> images;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    @Override
    public String toString() {
        return "BeanHomeResults{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", images=" + images +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String get_id() {

        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public List<String> getImages() {
        return images;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isUsed() {
        return used;
    }

    public String getWho() {
        return who;
    }
}
