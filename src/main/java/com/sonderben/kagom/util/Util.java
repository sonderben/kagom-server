package com.sonderben.kagom.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.List;

public class Util {

    public static String createToken(String user, List<String> roles) {
        String KEY = "awed8kfSdDSa8!m";
        Algorithm algorithm=Algorithm.HMAC256(KEY.getBytes());
        String a= JWT.create()
                .withSubject(user)
                .withIssuer("sonderben")
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*100000))
                .withClaim("roles",roles)
                .sign(algorithm);
        System.err.println("json created: ");

        return a;
    }
}
