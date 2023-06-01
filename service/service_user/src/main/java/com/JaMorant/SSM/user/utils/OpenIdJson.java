package com.JaMorant.SSM.user.utils;

/**
 * @author:JaMorant
 * @time:2023/2/18 11:37
 * @explain:
 */
public class OpenIdJson {
    private String openid;
    private String session_key;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
