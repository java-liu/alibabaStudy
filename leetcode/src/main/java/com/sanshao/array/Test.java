package com.sanshao.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        System.out.println(roles.length);
        Arrays.stream(roles).forEach(System.out::println);



        List<String>  list = Arrays.stream(roles).filter(ro -> ro.equals("4")).collect(Collectors.toList());
        list.stream().forEach(System.out::println);
        List<Integer> iList = new ArrayList<>();
        iList.add(1);
        iList.add(2);
        iList.add(3);
        iList.add(4);
        for (Integer integer : iList) {
            if(integer == 2){
                break;
            }
            //System.out.println(integer);
        }
        iList.forEach(integer -> {
            if(integer == 2){
                return;
            }
            System.out.println(integer);
        });

        Optional<Integer> result = iList.stream().filter(s -> s == 2).findFirst();
        System.out.println(result.orElse(null));

        boolean lxk = iList.stream().anyMatch(s -> s == 2);
        System.out.println(lxk);

    }
}
