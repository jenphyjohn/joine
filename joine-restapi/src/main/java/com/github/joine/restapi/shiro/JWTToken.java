package com.github.joine.restapi.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/16 1:44 PM
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
