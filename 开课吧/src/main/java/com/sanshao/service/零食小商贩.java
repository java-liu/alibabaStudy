package com.sanshao.service;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2020/6/15 16:29
 * @Version: 1.0
 */
public abstract class 零食小商贩 implements 学生守则接口 {
    /***
     * 考试后家长签字    ----- 不受实现类欢迎的方法
     */
    @Override
    public void signName() {
        System.out.println("家长签字！");

    }

}
