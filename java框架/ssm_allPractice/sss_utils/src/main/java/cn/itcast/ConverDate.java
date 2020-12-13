package cn.itcast;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverDate implements Converter<String,Date> {
    @Override
    public Date convert(String s) {
        if (s == null){
            throw new RuntimeException("请您输入日期");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换失败");
        }

    }
}
