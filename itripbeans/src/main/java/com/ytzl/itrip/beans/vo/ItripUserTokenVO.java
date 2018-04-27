package com.ytzl.itrip.beans.vo;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
public class ItripUserTokenVO {
    private Long expTime; // 过期时间
    private Long genTime; // 创建时间
    private String token; // token

    public ItripUserTokenVO(String token, Long expTime, Long genTime) {
        this.expTime = expTime;
        this.genTime = genTime;
        this.token = token;
    }

    public ItripUserTokenVO() {
    }
    public Long getExpTime() {
        return expTime;
    }

    public void setExpTime(Long expTime) {
        this.expTime = expTime;
    }

    public Long getGenTime() {
        return genTime;
    }

    public void setGenTime(Long genTime) {
        this.genTime = genTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
