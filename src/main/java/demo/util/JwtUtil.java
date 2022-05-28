package demo.util;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import demo.domain.Token;
import demo.domain.Yonghu;
import demo.security.domain.LoginUser;
import demo.service.IYonghuService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    public static final String JWT_KEY = "stickyfinger";

    public static final Long expTime =60*60*1000L*24*30;

    public static Token createToken(LoginUser loginUser){
        Yonghu yonghu = loginUser.getYonghu();
        Date now = TimeUtils.getDate();
        Date exp = TimeUtils.getDare(expTime);


        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setId(String.valueOf(yonghu.getId()))
                .claim("yonghuming",yonghu.getYonghuming())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,JWT_KEY);
        String tok = jwtBuilder.compact();

        Token token = new Token();
        token.setUserid(yonghu.getId());
        token.setUsername(yonghu.getYonghuming());
        token.setTablename("yonghu");
        token.setToken(tok);
        List<String> permissions = loginUser.getRoles();
        token.setRole(String.valueOf(permissions));
        token.setAddtime(TimeUtils.getTime(now));
        token.setExpiratedtime(TimeUtils.getTime(exp));
        return token;
    }

    public static Claims parseToken(String token){
        return Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token).getBody();
    }



}
