package com.example.springbootjwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class SpringbootJwtApplicationTests {

    /*
    生成token
     */
    @Test
    void contextLoads() {

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        HashMap<String,Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String sign = JWT.create()
                .withHeader(map)// header 可以省略（有默认值）
                .withClaim("userid", 12)// payload
                .withClaim("username", "lisi")// payload
                .withExpiresAt(instance.getTime())// 过期时间
                .sign(Algorithm.HMAC256("ABCDF"));// 签名

        System.out.println(sign);
    }

    /*
    验证令牌
     */
    @Test
    void test02() {
        // 使用签名生成认证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ABCDF")).build();
        // 解析token后的对象
//        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDEyMTk5MTcsInVzZXJpZCI6MTIsInVzZXJuYW1lIjoibGlzaSJ9.tvORT27FSBiByI7jPW8D_RSVDze1ivLqTgxOVwM8lr8");
//        System.out.println(verify.getClaims().get("userid").asInt());
//        System.out.println(verify.getClaims().get("username").asString());

    }

}
