package com.github.joine.restapi.domain;

import javax.validation.constraints.NotBlank;

/**
 * @Author: JenphyJohn
 * @Date: 2019/6/2 12:05 AM
 */
public class TokenBean {

    @NotBlank
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenBean(@NotBlank String token) {
        this.token = token;
    }

    public TokenBean() {
    }
}
