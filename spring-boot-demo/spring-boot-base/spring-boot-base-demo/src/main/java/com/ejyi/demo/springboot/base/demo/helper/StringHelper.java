package com.ejyi.demo.springboot.base.demo.helper;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.regex.Pattern;

public class StringHelper {


    public static boolean isInteger(String str) {
        return str != null && Pattern.matches("^\\d+$", str);
    }

    public static boolean isNumber(String str) {
        return str != null && Pattern.matches("^\\d+(.\\d+)?$", str);
    }

    /**
     * 获取uuid
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString();
    }


    /**
     * 手机号中间加星
     * @param mobile
     * @return
     */
    public static String hideMobile(String mobile){
        if(isEmpty(mobile)){
            return mobile;
        }
        String m = mobile.trim();
        if(m.length() <= 6){
            return mobile;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            char c = m.charAt(i);
            if (i >= 3 && i <= 6) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 数字前填充字符变字符串
     * @param num
     * @param len
     * @param preVal
     * @return
     */
    public static String addPreForNum(String num, Integer len, String preVal){
        String format = "%"+len+"s";
        return String.format(format, num).replace(" ",preVal);
    }

    public static String EMPTY = "";

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    /**
     * 分割字符串为数组
     * @param str
     * @param span
     * @return
     */
    public static String[] split(String str, String span){
        return StringUtils.split(str, span);
    }


    /**
     * 替换字符串中字符
     * @param str
     * @param oldstr
     * @param newstr
     * @return
     */
    public static String replace(String str, String oldstr, String newstr){
        return StringUtils.replace(str, oldstr, newstr);
    }

    /**
     * 合并字符串
     * @param strs
     * @param span
     * @return
     */
    public static String join(String[] strs, String span){
        return StringUtils.join(strs, span);
    }

    /**
     * 获取uuid
     * @return
     */
    public static String getUuid2(){
        return UUID.randomUUID().toString().replace("-", "");
    }




    public static void main(String[] args) {
        //Future
//        System.out.println(JsonHelper.toJson(StringHelper.split("1,2,3", ",")));
//        String[] strs = new String[]{"a", "b", "c", "d"};
//        System.out.println(StringHelper.join(strs, ","));
//
//        System.out.println(StringHelper.replace("a-ab-ad-a-cz", "-", "*"));

        String mobile = "18912347896";
        System.out.println(hideMobile(mobile));

    }

}

