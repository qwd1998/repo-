package cn.itcast;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String dateToString(Date date,String pat){
        SimpleDateFormat sf = new SimpleDateFormat(pat);
        String format = sf.format(date);
        return format;
    }

    public static Date StringToDate(String date,String pat) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(pat);
        Date parse = sf.parse(date);
        return parse;
    }
    public static void passwordLock(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String user = bCryptPasswordEncoder.encode(password);
        System.out.println(user);
    }

    public static void main(String[] args) {
        passwordLock("tom");

    }
}
