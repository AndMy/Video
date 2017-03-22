package com.home.quhong.quhong.TV.entity.detail;

import java.util.List;

/**
 * Created by aserbao on 2017/3/22.
 */

public class VideoDetail {

    /**
     * info : {"category":["action","comedy"],"title":"Free Fire ( 2017 )","series":[{"title":"Player 1","durl":"http://www.qmovies.tv:8080/v1/vdown?type=yt&code=oDD3I0uOlqY","purl":"http://www.qmovies.tv:8080/v1/vplay?type=yt&code=oDD3I0uOlqY"}],"dub":"Hindi","cover":"http://www.hdmovieskit.com/wp-content/uploads/2016/11/Free-Fire-Full-Movie-Download-2017-HD-720p-BluRay-202x300.png","vip":false,"director":"Ben Wheatley","duration":"123 minutes","hot":0,"classes":"movie","stars":"Brie Larson,Sharlto Copley","country":"IN","release":"31 March 2017","id":"58c8f8f61bad4863abd7def0","size":"700MB"}
     * code : 0
     * recommend : [{"status":"","rating":"6.2","title":"Dishoom","cover":"/static/cover/id/dishoom.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"id":"58b8d8cc1bad481c92347f2d"},{"status":"","rating":"4.1","title":"A Flying Jatt","cover":"/static/cover/id/a-flying-jatt.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"id":"58b8d8fd1bad481c92347f64"},{"status":"","rating":"5.5","title":"Housefull 3","cover":"/static/cover/id/housefull-3.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"id":"58b8d9371bad481c92347f9c"},{"vip":false,"cover":"https://1.bp.blogspot.com/-hXIZUynL3AI/WIh9mGjvLGI/AAAAAAAACeY/5ir4YeTZtYYaKopYEIwwUYUUsjJrw_6JACLcB/s1600/PH2fbNY79Jec53_1_l.jpg","id":"58c8f54e1bad486389fe5728","title":"Kung Fu Yoga Dual Audio Hindi Dubbed"},{"vip":false,"cover":"https://3.bp.blogspot.com/-gE6wXZQPq9c/WIccgxaJlaI/AAAAAAAACbo/N0h5IpUgjNkol0uDh7Cn3Iw8_hbOySyJQCLcB/s1600/chiranjeevi-khaidi-number-150-first-look-posters-rocking_b_2208160334.jpg","id":"58c8f55d1bad486389fe5732","title":"Khaidi No. 150"},{"vip":false,"cover":"https://4.bp.blogspot.com/-P2uLj2O-LX8/V5q1kgDZWOI/AAAAAAAAAbU/nFJ6Db59xMcl_ZVPD3gaTtZ4XYa0DVdZQCEw/s1600/tumblr_o7z8nqv2931u70fh7o1_500.jpg","id":"58c8f5a51bad486389fe5764","title":"Dishoom"},{"vip":false,"cover":"https://2.bp.blogspot.com/-6cLdt-U4J30/WGHVOwc8u-I/AAAAAAAACVs/YVlhqiFdQCUt2GBG7lY6RngM7U6RdNjWQCLcB/s1600/Screenshot_13.jpg","id":"58c8f5d01bad486389fe576e","title":"Secret Superstar"},{"vip":false,"cover":"http://www.hdmovieskit.com/wp-content/uploads/2016/09/kadavul-irukaan-kumaru-full-movie-download-hd-720p-hd-movies-download-com.jpg","id":"58c8f6a21bad4863abd7dd26","title":"Kadavul Irukan Kumaru tamil"},{"vip":false,"cover":"http://www.hdmovieskit.com/wp-content/uploads/2016/10/bruce-lee-full-movie-download-hd-tamil-free-2016-hdmovieskit.jpg","id":"58c8f6ce1bad4863abd7dd46","title":"Bruce Lee tamil"}]
     */
    private String rating;
    private String introduction;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    private InfoBean info;
    private int code;
    private List<RecommendBean> recommend;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class InfoBean {
        /**
         * category : ["action","comedy"]
         * title : Free Fire ( 2017 )
         * series : [{"title":"Player 1","durl":"http://www.qmovies.tv:8080/v1/vdown?type=yt&code=oDD3I0uOlqY","purl":"http://www.qmovies.tv:8080/v1/vplay?type=yt&code=oDD3I0uOlqY"}]
         * dub : Hindi
         * cover : http://www.hdmovieskit.com/wp-content/uploads/2016/11/Free-Fire-Full-Movie-Download-2017-HD-720p-BluRay-202x300.png
         * vip : false
         * director : Ben Wheatley
         * duration : 123 minutes
         * hot : 0
         * classes : movie
         * stars : Brie Larson,Sharlto Copley
         * country : IN
         * release : 31 March 2017
         * id : 58c8f8f61bad4863abd7def0
         * size : 700MB
         */

        private String title;
        private String dub;
        private String cover;
        private boolean vip;
        private String director;
        private String duration;
        private int hot;
        private String classes;
        private String stars;
        private String country;
        private String release;
        private String id;
        private String size;
        private List<String> category;
        private List<SeriesBean> series;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDub() {
            return dub;
        }

        public void setDub(String dub) {
            this.dub = dub;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRelease() {
            return release;
        }

        public void setRelease(String release) {
            this.release = release;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public List<String> getCategory() {
            return category;
        }

        public void setCategory(List<String> category) {
            this.category = category;
        }

        public List<SeriesBean> getSeries() {
            return series;
        }

        public void setSeries(List<SeriesBean> series) {
            this.series = series;
        }

        public static class SeriesBean {
            /**
             * title : Player 1
             * durl : http://www.qmovies.tv:8080/v1/vdown?type=yt&code=oDD3I0uOlqY
             * purl : http://www.qmovies.tv:8080/v1/vplay?type=yt&code=oDD3I0uOlqY
             */

            private String title;
            private String durl;
            private String purl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDurl() {
                return durl;
            }

            public void setDurl(String durl) {
                this.durl = durl;
            }

            public String getPurl() {
                return purl;
            }

            public void setPurl(String purl) {
                this.purl = purl;
            }
        }
    }

    public static class RecommendBean {
        /**
         * status :
         * rating : 6.2
         * title : Dishoom
         * cover : /static/cover/id/dishoom.jpg
         * cover_host : http://static.beemovieapp.com
         * vip : false
         * id : 58b8d8cc1bad481c92347f2d
         */

        private String status;
        private String rating;
        private String title;
        private String cover;
        private String cover_host;
        private boolean vip;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
