package com.zz.util;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/23
 * Time: 15:16
 * Description: No Description
 */
public class DateConverter implements Converter<String, Timestamp> {
    @Override
    public Timestamp convert(String source) {
        System.out.println("DateConverter111==============================================");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            System.out.println("DateConverter222==============================================");
            return new Timestamp(dateFormat.parse(source).getTime());
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
