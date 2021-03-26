package com.sanshao.jmm;

/**
 * @Description: 多线程情况下,method1 语句1,语句2出现指令重排会影响method2方法
 * @Author: Liuys
 * @CreateDate: 2021/3/19 13:13
 * @Version: 1.0
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method1(){
        a = 1;   //1
        flag = true;   //2
    }
    public void method2(){
        if(flag){
            a = a + 5;
            //结果可能是5,也可能是6
            //1.   a=1,flag=true
            //2. flag=true,之后,a=1并没有执行,a还是=0
            System.out.println("*****retValut:" + a);
        }
    }
    public static void main(String[] args){
        ReSortSeqDemo rsq = new ReSortSeqDemo();
        rsq.method1();
        rsq.method2();
    }

}
