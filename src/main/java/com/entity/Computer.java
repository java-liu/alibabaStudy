package com.entity;

import com.exceptions.BlueScreenException;
import com.exceptions.SmokeException;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.entity
 * @date 2020/1/16 14:56
 */
public class Computer {
    private int state = 0;
    public void run() throws BlueScreenException, SmokeException {
        if(state == 1)
            throw new BlueScreenException("电脑蓝屏了！");
        if(state == 2){
            throw new SmokeException("电脑冒烟了");
        }
        System.out.println("电脑正在运行！");
    }
    public void reset(){
        state = 0;
        System.out.println("电脑重启了");
    }

}

