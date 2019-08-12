package com.github.joine.restapi.util;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: JenphyJohn
 * @Date: 2019/4/16 4:11 PM
 */
public class JWTUtil {

    /**
     * 密钥,暂时写死
     */
    private static final String SECRET_KEY = "5371f568a45e5ab1f442c38e0932aef24447139b";

    /**
     * 过期时间2天
     */
    private static final long EXPIRE_TIME = 2 * 24 * 60 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String loginName, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("loginName", loginName)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getLoginName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getTokenInfo(String token, String info) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(info).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成带过期时间的签名
     *
     * @param loginName 用户名
     * @param secret    用户的密码
     * @return 加密的token
     */
    public static String sign(String loginName, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带loginName信息
        return JWT.create()
                .withClaim("loginName", loginName)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 生成带过期时间的签名
     *
     * @param wxMaJscode2SessionResult 微信认证result
     * @return 加密的token
     */
    public static String signWeChat(WxMaJscode2SessionResult wxMaJscode2SessionResult) {
        String jwtId = UUID.randomUUID().toString();
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        // 附带jwtId openid sessionKey信息
        String token = JWT.create()
                .withClaim("jwtId", jwtId)
                .withClaim("openid", wxMaJscode2SessionResult.getOpenid())
                .withClaim("sessionKey", wxMaJscode2SessionResult.getSessionKey())
                .withExpiresAt(date)
                .sign(algorithm);
        return token;
    }

    /**
     * 校验token是否正确
     *
     * @param token 令牌
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}
