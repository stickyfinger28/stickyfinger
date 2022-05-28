package demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassWordUtil {

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String encode(String mima){


        return passwordEncoder.encode(mima);
    }

    public static Boolean matches(String mima,String sqlmima){

        return passwordEncoder.matches(mima, sqlmima);
    }
}
