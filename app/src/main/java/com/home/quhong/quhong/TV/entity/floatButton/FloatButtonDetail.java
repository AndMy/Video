package com.home.quhong.quhong.TV.entity.floatButton;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aserbao on 2017/3/15.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class FloatButtonDetail {

    /**
     * category : {"18":"18+","mystery":"Mystery","drama":"Drama","all":"All","sci-fi":"Sci-fi","family":"Family","horror":"Horror","crime":"Crime","romance":"Romance","fantasy":"Fantasy","animation":"Animation","adventure":"Adventure","biography":"Biography","action":"Action","comedy":"Comedy","thriller":"Thriller"}
     * code : 0
     * country : {"all":"All","CN":"China","HK":"Hong Kong","JP":"Japan","FR":"France","US":"United States","KR":"Korea-South","GB":"Great Britain","TH":"Thailand","IN":"Indian","ID":"Indonesia"}
     * classes : {"drama":"SERIAL BARAT","movie":"FILM","all":"All"}
     * next_url : http://www.indiadsp.com:9998/search?classes=drama&category=romance&country=KR&order=hot&language=&start=10&end=20
     * data : [{"category":["drama","romance"],"rating":"8.6","title":"Goblin","cover":"/static/cover/id/goblin1487213040587.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"complete","id":"58b8d7ab1bad481c92347de8"},{"category":["comedy","romance"],"rating":"9.4","title":"The Legend of the Blue Sea","cover":"/static/cover/id/the-legend-of-the-blue-sea1479897449174.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"complete","id":"58b8d7ac1bad481c92347de9"},{"category":["comedy","romance"],"rating":"8.6","title":"Introverted Boss","cover":"/static/cover/id/introverted-boss1487043383643.gif","cover_host":"http://static.beemovieapp.com","vip":true,"status":"ongoing","id":"58b8d7ac1bad481c92347dea"},{"category":["drama","romance"],"rating":"8.6","title":"Boys Over Flower","cover":"/static/cover/id/boys-over-flower1479952989569.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7ad1bad481c92347deb"},{"category":["comedy","romance"],"rating":"8.6","title":"Flower Boy Next Door","cover":"/static/cover/id/flower-boy-next-door1478228044853.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7ba1bad481c92347df8"},{"category":["romance","comedy"],"rating":"8.3","title":"Descendants of the Sun","cover":"/static/cover/id/1-Descendants_of_the_Sun.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7bb1bad481c92347dfa"},{"category":["comedy","historical","romance"],"rating":"8.6","title":"Hwarang","cover":"/static/cover/id/hwarang1482294649585.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"complete","id":"58b8d7bb1bad481c92347dfb"},{"category":["romance","comedy"],"rating":"9.2","title":"Uncontrollably Fond","cover":"/static/cover/id/Uncontrollably-Fond.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7bc1bad481c92347dfd"},{"category":["romance","comedy"],"rating":"8.6","title":"Tomorrow with You","cover":"/static/cover/id/tomorrow-with-you1486974684463.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"ongoing","id":"58b8d7bc1bad481c92347dfe"},{"category":["comedy","romance"],"rating":"8.6","title":"Defendant","cover":"/static/cover/id/defendant1485763417415.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"ongoing","id":"58b8d7c21bad481c92347e00"}]
     * order : {"new":"newest","hot":"hottest"}
     */

    private CategoryBean category;
    private int code;
    private CountryBean country;
    private ClassesBean classes;
    private String next_url;
    private OrderBean order;
    private List<DataBean> data;

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CountryBean getCountry() {
        return country;
    }

    public void setCountry(CountryBean country) {
        this.country = country;
    }

    public ClassesBean getClasses() {
        return classes;
    }

    public void setClasses(ClassesBean classes) {
        this.classes = classes;
    }

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class CategoryBean {
        /**
         * 18 : 18+
         * mystery : Mystery
         * drama : Drama
         * all : All
         * sci-fi : Sci-fi
         * family : Family
         * horror : Horror
         * crime : Crime
         * romance : Romance
         * fantasy : Fantasy
         * animation : Animation
         * adventure : Adventure
         * biography : Biography
         * action : Action
         * comedy : Comedy
         * thriller : Thriller
         */

        @SerializedName("18")
        private String _$18;
        private String mystery;
        private String drama;
        private String all;
        @SerializedName("sci-fi")
        private String scifi;
        private String family;
        private String horror;
        private String crime;
        private String romance;
        private String fantasy;
        private String animation;
        private String adventure;
        private String biography;
        private String action;
        private String comedy;
        private String thriller;

        public String get_$18() {
            return _$18;
        }

        public void set_$18(String _$18) {
            this._$18 = _$18;
        }

        public String getMystery() {
            return mystery;
        }

        public void setMystery(String mystery) {
            this.mystery = mystery;
        }

        public String getDrama() {
            return drama;
        }

        public void setDrama(String drama) {
            this.drama = drama;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getScifi() {
            return scifi;
        }

        public void setScifi(String scifi) {
            this.scifi = scifi;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public String getHorror() {
            return horror;
        }

        public void setHorror(String horror) {
            this.horror = horror;
        }

        public String getCrime() {
            return crime;
        }

        public void setCrime(String crime) {
            this.crime = crime;
        }

        public String getRomance() {
            return romance;
        }

        public void setRomance(String romance) {
            this.romance = romance;
        }

        public String getFantasy() {
            return fantasy;
        }

        public void setFantasy(String fantasy) {
            this.fantasy = fantasy;
        }

        public String getAnimation() {
            return animation;
        }

        public void setAnimation(String animation) {
            this.animation = animation;
        }

        public String getAdventure() {
            return adventure;
        }

        public void setAdventure(String adventure) {
            this.adventure = adventure;
        }

        public String getBiography() {
            return biography;
        }

        public void setBiography(String biography) {
            this.biography = biography;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getComedy() {
            return comedy;
        }

        public void setComedy(String comedy) {
            this.comedy = comedy;
        }

        public String getThriller() {
            return thriller;
        }

        public void setThriller(String thriller) {
            this.thriller = thriller;
        }
    }

    public static class CountryBean {
        /**
         * all : All
         * CN : China
         * HK : Hong Kong
         * JP : Japan
         * FR : France
         * US : United States
         * KR : Korea-South
         * GB : Great Britain
         * TH : Thailand
         * IN : Indian
         * ID : Indonesia
         */

        private String all;
        private String CN;
        private String HK;
        private String JP;
        private String FR;
        private String US;
        private String KR;
        private String GB;
        private String TH;
        private String IN;
        private String ID;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getCN() {
            return CN;
        }

        public void setCN(String CN) {
            this.CN = CN;
        }

        public String getHK() {
            return HK;
        }

        public void setHK(String HK) {
            this.HK = HK;
        }

        public String getJP() {
            return JP;
        }

        public void setJP(String JP) {
            this.JP = JP;
        }

        public String getFR() {
            return FR;
        }

        public void setFR(String FR) {
            this.FR = FR;
        }

        public String getUS() {
            return US;
        }

        public void setUS(String US) {
            this.US = US;
        }

        public String getKR() {
            return KR;
        }

        public void setKR(String KR) {
            this.KR = KR;
        }

        public String getGB() {
            return GB;
        }

        public void setGB(String GB) {
            this.GB = GB;
        }

        public String getTH() {
            return TH;
        }

        public void setTH(String TH) {
            this.TH = TH;
        }

        public String getIN() {
            return IN;
        }

        public void setIN(String IN) {
            this.IN = IN;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

    public static class ClassesBean {
        /**
         * drama : SERIAL BARAT
         * movie : FILM
         * all : All
         */

        private String drama;
        private String movie;
        private String all;

        public String getDrama() {
            return drama;
        }

        public void setDrama(String drama) {
            this.drama = drama;
        }

        public String getMovie() {
            return movie;
        }

        public void setMovie(String movie) {
            this.movie = movie;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }

    public static class OrderBean {
        /**
         * new : newest
         * hot : hottest
         */

        @SerializedName("new")
        private String newX;
        private String hot;

        public String getNewX() {
            return newX;
        }

        public void setNewX(String newX) {
            this.newX = newX;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }
    }

    public static class DataBean {
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
}
