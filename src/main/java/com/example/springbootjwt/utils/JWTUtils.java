package com.example.springbootjwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @Author wendongchao
 * @Date 2022-01-03 22:31
 */
public class JWTUtils {

    private static final String SIGN = "ABCDF";

    /*
    获取token
     */
    public static String getToken(Map<String,String> map) {
        // 过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);// 默认7天过期

        // 创建jwt的builder
        JWTCreator.Builder builder = JWT.create();

        // 填充payload参数
        map.forEach((k,v) -> {
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())// 过期时间
                .sign(Algorithm.HMAC256(SIGN));// 签名

        return token;
    }

    /*
    校验并获取token
     */
    public static DecodedJWT verify(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }

}
