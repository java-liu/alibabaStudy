package com.easyconding;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.easyconding
 * @date 2019/9/18 11:32
 */
public class TestMap {
    public static void main(String [] args) {
        Map<String,String[]> map = new HashMap<>();
        String[] md5s = {"543s", "6765dd"};
        map.put("fileMd5",md5s);
        if(map.containsKey("fileMd522")){
            System.out.println(map.get("fileMd5") [0]);
        }else if(1==13){
            System.out.println(111);
        }else if(2==2){
            System.out.println(222);
        }
    }
}
