package com.home.quhong.quhong.TV.entity.filtrate;

import java.util.List;

/**
 * Created by aserbao on 2017/3/23.
 */

public class Filtrate {


    /**
     * category : [{"k":"all","v":"All"},{"k":"sci-fi","v":"Sci-fi"},{"k":"family","v":"Family"},{"k":"horror","v":"Horror"},{"k":"fantasy","v":"Fantasy"},{"k":"adventure","v":"Adventure"},{"k":"biography","v":"Biography"},{"k":"mystery","v":"Mystery"},{"k":"romance","v":"Romance"},{"k":"18","v":"18+"},{"k":"crime","v":"Crime"},{"k":"drama","v":"Drama"},{"k":"animation","v":"Animation"},{"k":"thriller","v":"Thriller"},{"k":"action","v":"Action"},{"k":"comedy","v":"Comedy"}]
     * code : 0
     * next_url : http://www.qmovies.tv:8080/v1/search?classes=drama&category=romance&country=KR&order=hot&language=&page=2
     * country : [{"k":"all","v":"All"},{"k":"HK","v":"Hong Kong"},{"k":"FR","v":"France"},{"k":"CN","v":"China"},{"k":"TH","v":"Thailand"},{"k":"KR","v":"Korea-South"},{"k":"IN","v":"Indian"},{"k":"JP","v":"Japan"},{"k":"ID","v":"Indonesia"},{"k":"US","v":"United States"},{"k":"GB","v":"Great Britain"}]
     * choosed : {"category":"romance","country":"KR","classes":"drama","order":"hot"}
     * classes : [{"k":"all","v":"All"},{"k":"drama","v":"SERIAL BARAT"},{"k":"movie","v":"FILM"}]
     * data : [{"category":["drama","romance"],"rating":"8.6","title":"Goblin","cover":"/static/cover/id/goblin1487213040587.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"complete","id":"58b8d7ab1bad481c92347de8"},{"category":["comedy","romance"],"rating":"9.4","title":"The Legend of the Blue Sea","cover":"/static/cover/id/the-legend-of-the-blue-sea1479897449174.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"complete","id":"58b8d7ac1bad481c92347de9"},{"category":["comedy","romance"],"rating":"8.6","title":"Introverted Boss","cover":"/static/cover/id/introverted-boss1487043383643.gif","cover_host":"http://static.beemovieapp.com","vip":true,"status":"ongoing","id":"58b8d7ac1bad481c92347dea"},{"category":["drama","romance"],"rating":"8.6","title":"Boys Over Flower","cover":"/static/cover/id/boys-over-flower1479952989569.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7ad1bad481c92347deb"},{"category":["comedy","romance"],"rating":"8.6","title":"Flower Boy Next Door","cover":"/static/cover/id/flower-boy-next-door1478228044853.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7ba1bad481c92347df8"},{"category":["romance","comedy"],"rating":"8.3","title":"Descendants of the Sun","cover":"/static/cover/id/1-Descendants_of_the_Sun.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7bb1bad481c92347dfa"},{"category":["comedy","historical","romance"],"rating":"8.6","title":"Hwarang","cover":"/static/cover/id/hwarang1482294649585.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"complete","id":"58b8d7bb1bad481c92347dfb"},{"category":["romance","comedy"],"rating":"9.2","title":"Uncontrollably Fond","cover":"/static/cover/id/Uncontrollably-Fond.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7bc1bad481c92347dfd"},{"category":["romance","comedy"],"rating":"8.6","title":"Tomorrow with You","cover":"/static/cover/id/tomorrow-with-you1486974684463.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"ongoing","id":"58b8d7bc1bad481c92347dfe"},{"category":["comedy","romance"],"rating":"8.6","title":"Defendant","cover":"/static/cover/id/defendant1485763417415.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"ongoing","id":"58b8d7c21bad481c92347e00"}]
     * order : [{"k":"new","v":"newest"},{"k":"hot","v":"hottest"}]
     */

    private int code;
    private String next_url;
    private ChoosedBean choosed;
    private List<CategoryBean> category;
    private List<CountryBean> country;
    private List<ClassesBean> classes;
    private List<DataBean> data;
    private List<OrderBean> order;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

    public ChoosedBean getChoosed() {
        return choosed;
    }

    public void setChoosed(ChoosedBean choosed) {
        this.choosed = choosed;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<CountryBean> getCountry() {
        return country;
    }

    public void setCountry(List<CountryBean> country) {
        this.country = country;
    }

    public List<ClassesBean> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassesBean> classes) {
        this.classes = classes;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public static class ChoosedBean {
        /**
         * category : romance
         * country : KR
         * classes : drama
         * order : hot
         */

        private String category;
        private String country;
        private String classes;
        private String order;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }

    public static class CategoryBean {
        /**
         * k : all
         * v : All
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

    public static class CountryBean {
        /**
         * k : all
         * v : All
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

    public static class ClassesBean {
        /**
         * k : all
         * v : All
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



    public static class OrderBean {
        /**
         * k : new
         * v : newest
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
}
