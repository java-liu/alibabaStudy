package com.entity;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.entity
 * @date 2020/1/15 14:03
 */
public class Person {
    private String name;
    private int age;

    /***
     * 构造函数到底抛出这个IllegalArgumentException 是继承Exception呢？还是继承RuntimeException呢？
     * 继承Exception,必须要throws声明，一声明就告知调用者进行捕获，一旦问题处理了调用者的程序会继续执行。
     * 但是如果使用到了Person对象的数据，会导致都失败的。
     * 继承RuntimeException，不需要throws声明的，这是调用是不可能编写捕获代码的，因为调用根本就不知道有问题。
     * 一旦发生IllegalArgumentException，调用者程序会停掉，并有jvm将信息显示到屏幕，让调用者看到问题，然后修正代码。
     * @param name
     * @param age
     */

    public Person(String name, int age){
        //加入逻辑判断
        // （不过一般不这么写）
        if(age < 0 || age > 200){
            /***
             * 这样做虽然可以编译并运行看到消息，但是问题却没有发生，程序还在继续执行。并打印p对象。
             * 这是不合理的，人对象初始化过程已经出了问题，为何要会对人对象操作
             * 所以应该将问题暴露出来，让使用该程序的调用者知道，
             * 所以要使用异常来解决
             */
            /*System.out.println("年龄数值错误");
            return;*/
            // 抛出（运行时）异常，但是不用声明，不需要调用者处理。就需要一旦问题发生让调用者停止，让其修改代码。
            throw new IllegalArgumentException("年龄数值错误");
        }

        this.name = name;
        this.age = age;
    }

    @Override
    public String toString(){
        return "Person[name="+ name+",age="+ age +"]";
    }
}
