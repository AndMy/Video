package com.home.quhong.quhong.TV.entity.live;

import java.util.List;

/**
 * Created by aserbao on 2017/3/21.
 */

public class VideoDetail {

    /**
     * status :
     * rating : 6.4
     * title : The Hollars
     * url : /vinfo?vid=58b8d96f1bad481c92347fd7&language=IN
     * cover : /static/cover/id/the-hollars1482200792189.jpg
     * count : 0
     * cover_host : http://static.beemovieapp.com
     * vip : false
     * category : ["comedy","drama","romance"]
     * type : movie
     * id : 58b8d96f1bad481c92347fd7
     */

    private String status;
    private String rating;
    private String title;
    private String url;
    private String cover;
    private int count;
    private String cover_host;
    private boolean vip;
    private String type;
    private String id;
    private List<String> category;

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

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
