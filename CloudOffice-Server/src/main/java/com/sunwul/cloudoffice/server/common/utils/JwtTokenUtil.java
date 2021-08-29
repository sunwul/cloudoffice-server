package com.sunwul.cloudoffice.server.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*****
 * @author sunwul
 * @date 2021/3/21 11:45
 * @description Jwt_Token工具类
 */
@Component
public class JwtTokenUtil {

    // 用户名的key
    private static final String CLAIM_KEY_USERNAME = "sub";
    // jwt创建时间
    private static final String CLAIM_KEY_CREATED = "created";
    // 秘钥,通过yml配置文件去拿
    @Value("${jwt.secretKey}")
    private String secretKey;
    // 失效时间,通过yml配置文件去拿
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 1. 根据用户信息生成token
     *
     * @param userDetails 用户信息从security的UserDetails中获取
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        // 组装载荷信息
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        // 根据载荷信息创建token
        return generateToken(claims);
    }



    /**
     * 2. 从token中获取登录用户名
     *
     * @param token token
     * @return 登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            // 从token中获取载荷
            Claims claims = getClaimsFromToken(token);
            // 从载荷中拿到登录用户名
            username = claims.getSubject(); // DefaultClaims   getSubject()  SUBJECT = "sub"
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    /**
     * 3. 验证token是否有效  验证用户名是否正确, 判断token是否失效
     *
     * @param token token
     * @param userDetails UserDetails
     * @return 验证结果
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        // 从token中获取登录用户名
        String username = getUserNameFromToken(token);
        // 验证用户名,验证token是否失效
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 4. 判断token是否可以被刷新
     *
     * @param token token
     * @return 判断结果
     */
    public boolean canRefresh(String token) {
        // 验证token是否失效, 当token过期了表示可以被刷新
        return !isTokenExpired(token);
    }

    /**
     * 5. 刷新token
     * @param token token
     * @return 刷新后的token
     */
    public String refreshToken(String token){
        // 获取载荷
        Claims claims = getClaimsFromToken(token);
        // 载荷中放入新的失效时间(刷新token基本只改变token的过期时间)
        claims.put(CLAIM_KEY_CREATED,new Date());
        // 根据载荷生成 JWT TOKEN
        return generateToken(claims);
    }



    /**
     * 根据载荷生成 JWT TOKEN
     * 载荷中放置了 token 的一些基本信息，以帮助接受它的服务器来理解这个 token。同时还可以包含一些自定义的信息，用户信息交换
     *
     * @param claims token的基本信息
     * @return token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 放入载荷
                .setClaims(claims)
                // 设置失效时间
                .setExpiration(generateExpirationDate())
                // 签名
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * 生成token失效时间
     *
     * @return token失效时间
     */
    private Date generateExpirationDate() {
        // 失效时间(毫秒) = 当前系统时间 + 配置的时间
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取载荷
     *
     * @param token token
     * @return 载荷 载荷中放置了 token 的一些基本信息，以帮助接受它的服务器来理解这个 token。同时还可以包含一些自定义的信息，用户信息交换
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    // 签名秘钥
                    .setSigningKey(secretKey)
                    // 解析载荷
                    .parseClaimsJws(token)
                    // 拿到其中的信息
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 从token中获取失效时间
     *
     * @param token token
     * @return 时间
     */
    private Date getExpirationDateFromToken(String token) {
        // 从token中获取载荷
        Claims claims = getClaimsFromToken(token);
        // 返回过期时间
        return claims.getExpiration();
    }

    /**
     * 判断token是否失效
     *
     * @param token token
     * @return 验证结果
     */
    private boolean isTokenExpired(String token) {
        // 从token中获取失效时间
        Date expirationDate = getExpirationDateFromToken(token);
        // 判断失效时间是否在当前时间前, 过期时间.before.当前时间(过期时间小于当前时间返回true)
        return expirationDate.before(new Date());
    }

}
