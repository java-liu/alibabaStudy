package com.maizi.thread;

import javax.management.relation.RelationNotFoundException;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.maizi.thread
 * @date 2019/8/2 10:51
 */
public class Test{
    public static void main(String[] args) throws InterruptedException{
        Foo2 foo = new Foo2();
        foo.first(new Runnable() {
            @Override
            public void run() {
                foo.one();
            }
        });

        foo.second(new Runnable() {
            @Override
            public void run() {
                foo.two();
            }
        });
        foo.third(new Runnable() {
            @Override
            public void run() {
                foo.three();
            }
        });

            FooBar fooBar = new FooBar(2);
            fooBar.foo(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Foo");
                }
            });
            fooBar.bar(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Bar");
                }
            });
        }

}
