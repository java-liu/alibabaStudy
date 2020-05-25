package com.maizi.utils;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2020/4/14 17:49
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args){
       // StringBuilder sb = new StringBuilder();
        //System.out.println(getLetter(sb.toString()));
        System.out.println(getsb());
        String str = "2020年第5号";
        String str1 = str.substring(str.indexOf("第")+1,str.length()-1);
        System.out.println(str1);
    }
    /***
     * 取出字符串所有字母（字符）
     * @param str
     * @return
     */
    public static String getLetter(String str){
        if(StringUtils.isBlank(str))
            return "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if((c <= 'z' && c >= 'a') ||(c <= 'Z' && c >= 'A')){
                sb.append(c);
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    public static String getsb(){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        return sb.toString();
    }
}
