package com.home.quhong.quhong.TV.entity.filtrate;

import java.util.List;

/**
 * Created by aserbao on 2017/3/24.
 */

public class DataBean {
    /**
     * category : ["drama","romance"]
     * rating : 8.6
     * title : Goblin
     * cover : /static/cover/id/goblin1487213040587.jpg
     * cover_host : http://static.beemovieapp.com
     * vip : true
     * status : complete
     * id : 58b8d7ab1bad481c92347de8
     */

    private String rating;
    private String title;
    private String cover;
    private String cover_host;
    private boolean vip;
    private String status;
    private String id;
    private List<String> category;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
