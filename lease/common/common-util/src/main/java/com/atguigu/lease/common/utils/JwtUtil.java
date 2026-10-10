package com.atguigu.lease.common.utils;

import com.atguigu.lease.common.exception.LeaseException;
import com.atguigu.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    public static SecretKey secretKey = Keys.hmacShaKeyFor("MGRy8GUfKmub2aVHWA7HS6NQuUJHrDRg".getBytes());

    public static String createToken(Long userId,String username){
        String jwt = Jwts.builder().
                setExpiration(new Date(System.currentTimeMillis() + 3600000)).
                setSubject(username).
                claim("userId",userId).
                claim("username",username).
                signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    public static Claims parseToken(String token){
        if(token == null){
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return claims;
        }catch (ExpiredJwtException e){
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException  e){
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }

    }


    public static void main(String[] args) {
        String token = JwtUtil.createToken(8L,"13890930064");
        System.out.println(token);
    }
}
