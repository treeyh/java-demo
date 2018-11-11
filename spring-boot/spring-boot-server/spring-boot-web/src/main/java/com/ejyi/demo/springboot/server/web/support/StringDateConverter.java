/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 提交参数日期转换
 * Version: 1.0
 * Date: 18-5-9 下午2:55
 * LastModified: 18-5-9 下午2:55
 */

package com.ejyi.demo.springboot.server.web.support;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringDateConverter implements Converter<String, Date> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

    private static final String TIME_PATTERN_REGEX = "^\\d{1,13}$";

    @Override
    public Date convert(final String source) {
        if(source == null || source.trim().equals("")){
            return null;
        }
        String _src = source.trim();
        // 1,数字类型
        if(_src.matches(TIME_PATTERN_REGEX)){
            try{
                long lTime = Long.parseLong(_src);
                if(_src.length() > 10){
                    return new Date(lTime);
                }else{
                    return new Date(1000L * lTime);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
        if(_src.length() == 19){
            // 2,长日期类型
            try {
                dateFormat.setLenient(false);

                return dateFormat.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return null;
        }
        if(_src.length() == 10) {
            // 3,短日期类型
            try {
                dateFormat1.setLenient(false);

                return dateFormat1.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
