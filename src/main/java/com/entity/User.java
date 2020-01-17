package com.entity;

import com.exceptions.BlueScreenException;
import com.exceptions.MyException;
import com.exceptions.SmokeException;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.entity
 * @date 2020/1/16 15:03
 */
public class User {
    private String name;
    private Computer computer;

    User(String name){
        this.name = name;
        computer = new Computer();
    }


    /***
     * 使用中冒烟，问题可以临时解决，是冒烟问题没有直接处理，所以就使用throws声明。
     * 但是发现，这个问题不应该属于使用的问题，调用使用的调用者是处理不了这个问题的。
     * 该调用者能处理的应该是冒烟导致的使用进行不下去的问题。
     * @throws SmokeException
     */
    //使用
    public void use() throws MyException//throws SmokeException //throws BlueScreenException
    {
        /***
         * 调用到了声明异常的方法，声明好？还是捕获好？
         * 有具体的捕获处理方式？有，就捕获，没有，那就声明
         * user可以处理，重启就可以了，重启是电脑的功能
         */
        System.out.println(name + "用户正在使用1...");
        try{
            computer.run();
        }catch (BlueScreenException ex){
            //重启
            //异常的名称 + 异常的信息
            System.out.println(ex.toString());
            computer.reset();
        }
        catch(SmokeException se){
            //SmokeException se = new SmokeException("电脑冒烟了");
            System.out.println(se.toString());
            rest();
            //冒烟问题没有解决，继续声明throws出去
            //throw se;
            throw new MyException(se.getMessage() + "停止使用");//异常转换
        }
        //如果出现异常，catch里面解决完后，后面的程序会接着执行
        System.out.println(name + "用户正在使用2...");
    }

    //休息
    public void rest(){
        System.out.println("休息");
    }
}
