package com.home.quhong.quhong.TV.entity.filtrate;

import java.util.List;

/**
 * Created by aserbao on 2017/3/24.
 */

public class RefreshFiltrate {
    /**
     * next_url : http://www.qmovies.tv:8080/v1/search?classes=drama&category=romance&country=KR&order=hot&language=&page=3
     * choosed : {"category":"romance","country":"KR","classes":"drama","order":"hot"}
     * code : 0
     * data : [{"category":["comedy","romance"],"rating":"8.6","title":"Heartstrings","cover":"/static/cover/id/heartstrings1478228130126.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7c21bad481c92347e01"},{"category":["adventure","drama","romance"],"rating":"8.6","title":"The Lost Tomb","cover":"/static/cover/id/the-lost-tomb1486623065112.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7c41bad481c92347e04"},{"category":["comedy","romance"],"rating":"8.6","title":"The Miracle","cover":"/static/cover/id/the-miracle1485155157305.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7c41bad481c92347e05"},{"category":["romance","comedy","fantasy"],"rating":"8.9","title":"You Who Came From the Stars","cover":"/static/cover/id/You-Who-Came-From-the-Stars.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7c51bad481c92347e07"},{"category":["romance","comedy","school"],"rating":"7.6","title":"The Heirs","cover":"/static/cover/id/The-Heirs.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7c71bad481c92347e0b"},{"category":["comedy","romance"],"rating":"8.6","title":"The K2","cover":"/static/cover/id/the-k21475909210475.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7d11bad481c92347e0e"},{"category":["romance","comedy"],"rating":"9.5","title":"Marriage Contract","cover":"/static/cover/id/8-marrige-contract.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7d41bad481c92347e13"},{"category":["fantasy","comedy","romance"],"rating":"8.5","title":"Rooftop Prince","cover":"/static/cover/id/Rooftop-Prince.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7d51bad481c92347e15"},{"category":["comedy","romance"],"rating":"8.6","title":"Missing Nine","cover":"/static/cover/id/missing-nine1485763180854.jpg","cover_host":"http://static.beemovieapp.com","vip":true,"status":"ongoing","id":"58b8d7e01bad481c92347e19"},{"category":["fantasy","historical","romance"],"rating":"8.8","title":"Moon Lovers: Scarlet Heart Ryeo","cover":"/static/cover/id/moon-lovers--scarlet-heart-ryeo1477985356420.jpg","cover_host":"http://static.beemovieapp.com","vip":false,"status":"complete","id":"58b8d7e11bad481c92347e1a"}]
     */

    private String next_url;
    private ChoosedBean choosed;
    private int code;
    private List<DataBean> data;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
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
}
