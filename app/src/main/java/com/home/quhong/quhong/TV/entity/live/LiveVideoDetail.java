package com.home.quhong.quhong.TV.entity.live;

import java.util.List;

/**
 * Created by aserbao on 2017/3/21.
 */

public class LiveVideoDetail {

    /**
     * code : 0
     * classes : [{"k":"home","v":"BERANDA"},{"k":"movie","v":"FILM"},{"k":"ws","v":"SERIAL BARAT"},{"k":"kd","v":"KDRAMA"}]
     * search_keys : ["sk5","sk4","sk3","sk2","sk1"]
     * banner : {"videos":[]}
     * page : 2
     * card : [{},{},{},{},{},{"more_url":"http://www.qmovies.tv:8080/v1/search?&language=IN","more_title":"more","videos":[],"title":"Koleksi Spesial buat Tahun Baru"},{"more_url":"http://www.qmovies.tv:8080/v1/search?&language=IN","more_title":"more","videos":[],"title":"Film +18"},{"more_url":"http://www.qmovies.tv:8080/v1/search?&language=IN","more_title":"more","videos":[],"title":" Film Terpanas Korea"},{"more_url":"http://www.qmovies.tv:8080/v1/search?&language=IN","more_title":"more","videos":[],"title":"Film Terpanas Indonesia"}]
     */

    private int code;
    private BannerBean banner;
    private int page;
    private List<ClassesBean> classes;
    private List<String> search_keys;
    private List<CardBean> card;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ClassesBean> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassesBean> classes) {
        this.classes = classes;
    }

    public List<String> getSearch_keys() {
        return search_keys;
    }

    public void setSearch_keys(List<String> search_keys) {
        this.search_keys = search_keys;
    }

    public List<CardBean> getCard() {
        return card;
    }

    public void setCard(List<CardBean> card) {
        this.card = card;
    }

    public static class BannerBean {
        private List<VideoDetail> videos;

        public List<VideoDetail> getVideos() {
            return videos;
        }

        public void setVideos(List<VideoDetail> videos) {
            this.videos = videos;
        }

    }

    public static class ClassesBean {
        /**
         * k : home
         * v : BERANDA
         */

        private String k;
        private String v;

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }

    public static class CardBean {
        /**
         * more_url : http://www.qmovies.tv:8080/v1/search?&language=IN
         * more_title : more
         * videos : []
         * title : Koleksi Spesial buat Tahun Baru
         */

        private String more_url;
        private String more_title;
        private String title;
        private List<VideoDetail> videos;

        public String getMore_url() {
            return more_url;
        }

        public void setMore_url(String more_url) {
            this.more_url = more_url;
        }

        public String getMore_title() {
            return more_title;
        }

        public void setMore_title(String more_title) {
            this.more_title = more_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<VideoDetail> getVideos() {
            return videos;
        }

        public void setVideos(List<VideoDetail> videos) {
            this.videos = videos;
        }

    }
}
