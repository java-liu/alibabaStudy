package com.sanshao.jmm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 原子引用
 * @Author: Liuys
 * @CreateDate: 2021/3/22 12:08
 * @Version: 1.0
 */
public class AtomicReferenceDemo {
    public static void main(String[] args){
        User z3 = new User("zs",22);
        User li4 = new User("lisi", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
    }



}


@Getter
@Setter
@ToString
@AllArgsConstructor
class User{
    String userName;
    int age;
}
