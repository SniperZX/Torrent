package com.huanrong.velley;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class Headers {
    private Headers(){}

    private static Headers headers;

    public synchronized static Headers getInstance(){
        if(headers == null){
            headers = new Headers();
        }
        return headers;
    }

    String apptoken;

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getApptoken() {
        return apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }

    String appSecret;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    String language;

}
