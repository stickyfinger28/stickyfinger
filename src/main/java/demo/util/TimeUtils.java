package demo.util;

import io.swagger.annotations.ApiImplicitParam;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public  static String getTime() {
        long now = System.currentTimeMillis();
        return simpleDateFormat.format(now);

    }

    public  static String getTime(Date date) {
        return simpleDateFormat.format(date);

    }

    public static Date getDate(){
        long now = System.currentTimeMillis();
        return new Date(now);
    }

    public static Date getDare(Long addtime){
        long now = System.currentTimeMillis();
        return new Date(now+addtime);
    }
}
