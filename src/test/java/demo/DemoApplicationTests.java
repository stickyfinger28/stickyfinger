package demo;


import demo.domain.Yonghu;
import demo.mapper.YonghuMapper;
import demo.service.IYonghuService;
import demo.util.PassWordUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.PackageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private YonghuMapper yonghuMapper;

    @Autowired
    private IYonghuService yonghuService;


    @Test
    void get(){
        String mima="88381089a";
        String sqlmima = PassWordUtil.encode(mima);
        System.out.println(sqlmima);

        Boolean matches = PassWordUtil.matches(mima, sqlmima);
        System.out.println(matches);


    }




}
