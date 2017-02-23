package com.home.quhong.quhong.TV.entity.home;

/**
 * Created by aserbao on 2017/2/23.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class Video {

    /**
     * status :
     * rating : 8.9
     * title : La La Land
     * url : /v1/info/?dramaId=587d60f6cd6c8f03731abd7e&language=id
     * cover : /static/cover/id/la-la-land1487211738490.gif
     * count : 0
     * cover_host : http://static.beemovieapp.com
     * vip : true
     * genre : comedy,drama,musical
     * type : movie
     * id : 587d60f6cd6c8f03731abd7e
     */

    private String status;
    private String rating;
    private String title;
    private String url;
    private String cover;
    private int count;
    private String cover_host;
    private boolean vip;
    private String genre;
    private String type;
    private String id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCover_host() {
        return cover_host;
    }

    public void setCover_host(String cover_host) {
        this.cover_host = cover_host;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
