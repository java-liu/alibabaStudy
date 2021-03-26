package com.sanshao.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static int computer(int a, int b){
        int c = a + b;
        System.out.println(c);
        return c;
    }
    public static void main(String[] args){
        String role ="1,4";
        String[] roles = role.split(",");
        //System.out.println(roles.length);
        //Arrays.stream(roles).forEach(System.out::println);



        List<String>  list = Arrays.stream(roles).filter(ro -> ro.equals("3")).collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }
}
