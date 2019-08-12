package com.github.joine.restapi.result;

/**
 * @Author: JenphyJohn
 * @Date: 2019/7/26 9:21 AM
 */
public class LoginResult {

    private String token;

    private Long userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LoginResult(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public LoginResult() {
    }
}
