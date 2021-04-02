package cn.webro.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: zhanghuixin
 * @Date: 2020/11/12
 */
@IocBean
public class TokenUtils {

    @Inject
    private Dao dao;

    
    private static final long EXPIRE_DATE = 14400000;

    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";


    /**
     * @Description: 获取token
     * @Param: [username, password]
     * @return: java.lang.String
     * @Author: zhanghuixin
     * @Date: 2020/11/12
     */
    public static String token(String username, String password) {

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);


            System.out.println("返回token=====" + token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /***
     * @Description: 验证token
     * @Param: [token]
     * @return: boolean
     * @Author: zhanghuixin
     * @Date: 2020/11/12 
     */

    public static boolean verify(String token) {
        /**
         * @desc 验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            System.out.println("algorithm==" + algorithm);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public static void main(String[] args) {
////        String username = "lisi";
////        String password = "123";
////        String token = TokenUtils.token(username, password);
////        System.out.println("返回token=====" + token);
////        boolean b = TokenUtils.verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd22yZCI6IjEyMyIsImV4cCI6MTU3ODE5NzQxMywidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.IyTZT0tISQQZhGhsNuaqHGV8LD7idjUYjn3MGbulmJg");
//        System.out.println("解决token=====eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd22yZCI6IjEyMyIsImV4cCI6MTU3ODE5NzQxMywidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.IyTZT0tISQQZhGhsNuaqHGV8LD7idjUYjn3MGbulmJg");
////        System.out.println("结果++++++" + b);
//
//        boolean verify = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMyIsImV4cCI6MTYwNTE1ODcwNywidXNlcm5hbWUiOiJsaXNpIn0.1yQGT8yXSNYEp2Sm0Zmt36WZ4NMLe7fF_WjkrIHRm3E");
//        System.out.println(verify);
//    }
}
