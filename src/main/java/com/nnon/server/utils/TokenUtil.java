package com.nnon.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nnon.server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
@Component
public class TokenUtil {
    //过期时间 30分钟
    private static final long EXPIRE_TIME= 30*60*1000;
    private static final String TOKEN_SECRET="tokenqkj";  //密钥盐
    //刷新时间为小于5分钟就刷新
    private static final long RESETTIME =  5*60*1000;
    @Autowired
    private  RedisUtil utils;

    public RedisUtil getRedisUtil(){
        return utils;
    }

    /**
     * 签名生成
     * @return
     */
    public  String sign(Integer id , String role){

        String token = null;
        try {

            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withKeyId(id.toString())
                    .withClaim("role",role)
                    .withClaim("createTime",new Date())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
            utils.set(id.toString()+role,token,expiresAt.getTime());
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     * @param token
     * @param response
     * @return
     */
    public String verify(String token, HttpServletResponse response){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Claim role = jwt.getClaim("role");
            Date createTime = jwt.getClaim("createTime").asDate();
            System.out.println(createTime.getTime() - new Date().getTime() < RESETTIME);
            if(createTime.getTime()-new Date().getTime()<RESETTIME){
                System.out.println("准备过期");
                String sign = sign(Integer.parseInt(jwt.getKeyId()), role.asString());
                response.setHeader("authorization",sign);
                return jwt.getKeyId().toString();
            }else{
                Object o = utils.get(jwt.getKeyId()+ role.asString());
                if(o.toString().equals(token)){
                    return jwt.getKeyId().toString();
                }else {
                    return "";
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 签名验证
     * @param token
     * @return
     */
    public static String getUid(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("issuer: " + jwt.getIssuer());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return jwt.getKeyId().toString();
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

}

