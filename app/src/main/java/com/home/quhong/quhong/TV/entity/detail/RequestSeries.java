package com.home.quhong.quhong.TV.entity.detail;

/**
 * Created by aserbao on 2017/3/22.
 */

public class RequestSeries {

    /**
     * url : https://r4---sn-cvh7kn7l.googlevideo.com/videoplayback?key=yt6&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&expire=1490171641&upn=cL39uC8VtIc&itag=22&ratebypass=yes&ipbits=0&initcwndbps=5176250&mime=video%2Fmp4&signature=3A985E052ACD13B792683CAF8C5ED325D403AB99.129F449FE696E6B6E6C28842385EF98122A773C8&requiressl=yes&lmt=1473505144898306&ip=35.154.0.251&pl=16&dur=160.519&mv=m&source=youtube&ms=au&ei=mOLRWM6tO4iPoAOjkL3ICA&mn=sn-cvh7kn7l&mm=31&id=o-AJ1tzV3mMwhP923dpGZqLjd382RVYAcWq2OcJtsIKZSv&mt=1490149942
     * code : 0
     */

    private String url;
    private int code;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
