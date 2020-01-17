package com.exceptions;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.exceptions
 * @date 2020/1/15 13:39
 */
public class Demo1 {
    /***
     * 对给定的数组通过给定的角标获取元素
     * @param arr
     * @param index
     * @return
     */
    int getElements(int[] arr, int index){
        /***
         * jvm出了问题，自己打包对象并抛出。
         * 但是它所提供的信息不够详细，想要更清晰，需要自己写
         */
        if(arr == null){
            throw new NullPointerException("arr指定的数组不存在");
        }
        if(index < 0 || index >= arr.length){
            //该条件如果满足，功能已经不无法继续运算，这时就必须结束功能。并将问题告知给调用者，这是就需要通过异常来解决。
            //1.创建一个异常对象 。封装一些提示信息（自定义）
            //2.需要将这个对象告知给调用者，通过throw异常对象
            //ArrayIndexOutOfBoundsException有多个构造函数
            //throw new ArrayIndexOutOfBoundsException(index);
            //throw new ArrayIndexOutOfBoundsException(index);
            //throw new ArrayIndexOutOfBoundsException(index + "");
            throw new ArrayIndexOutOfBoundsException("错误的角标" + index + "不在数组中！");
        }
        int element = arr[index];
        return element;
    }

    public static void main(String[] args){
        Demo1 demo1 = new Demo1();
        int[] arr = {2,3,4};
        demo1.getElements(arr, 4);
    }
}
