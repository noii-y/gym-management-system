package com.noy.jwt;

// Java标准库
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

// 第三方库
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

// Spring框架
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT工具类
 * 
 * <p>提供JWT令牌的生成、验证和解析功能</p>
 * 
 * <p>主要功能包括：</p>
 * <ul>
 *   <li>令牌生成 - 根据自定义参数生成JWT令牌</li>
 *   <li>令牌验证 - 验证JWT令牌的有效性</li>
 *   <li>令牌解析 - 解析JWT令牌获取载荷信息</li>
 *   <li>异常处理 - 完善的JWT异常处理机制</li>
 * </ul>
 * 
 * @author noy
 * @version 1.0
 * @since 2024
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    /**
     * JWT令牌颁发者
     */
    private String issuer;
    
    /**
     * JWT签名密钥
     */
    private String secret;
    
    /**
     * JWT令牌过期时间（分钟）
     */
    private int expiration;
    // ==================== JWT令牌操作方法 ====================
    
    /**
     * 生成JWT令牌
     * 
     * <p>根据提供的自定义参数生成JWT令牌，包含颁发者、颁发时间、过期时间等信息</p>
     * 
     * @param map 自定义载荷参数，键值对形式
     * @return 生成的JWT令牌字符串
     */
    public String generateToken(Map<String, String> map) {
        // 设置令牌的过期时间
        Calendar instance = Calendar.getInstance();
        // 设置失效时间
        instance.add(Calendar.MINUTE, expiration);
        
        // 创建JWT builder
        JWTCreator.Builder builder = JWT.create();
        
        // 添加自定义载荷
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        
        // 构建并签名令牌
        String token = builder
                .withIssuer(issuer)                    // 设置颁发者
                .withIssuedAt(new Date())              // 设置颁发时间
                .withExpiresAt(instance.getTime())     // 设置过期时间
                .sign(Algorithm.HMAC256(secret));      // 使用HMAC256算法签名
        
        return token;
    }
    
    /**
     * 验证JWT令牌是否合法
     * 
     * <p>验证令牌的签名、算法、过期时间等是否有效</p>
     * 
     * @param token 待验证的JWT令牌
     * @return true表示令牌有效，false表示令牌无效
     */
    public boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (JWTVerificationException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
    /**
     * 解析JWT令牌
     * 
     * <p>解析JWT令牌并返回解码后的JWT对象，包含载荷信息</p>
     * 
     * @param token 待解析的JWT令牌
     * @return 解码后的JWT对象
     * @throws RuntimeException 当令牌签名错误、算法不匹配、过期或解析失败时抛出
     */
    public DecodedJWT jwtDecode(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (SignatureVerificationException e) {
            throw new RuntimeException("token签名错误!");
        } catch (AlgorithmMismatchException e) {
            throw new RuntimeException("token算法不匹配!");
        } catch (TokenExpiredException e) {
            throw new RuntimeException("token过期!");
        } catch (Exception e) {
            throw new RuntimeException("token解析失败!");
        }
    }
}
