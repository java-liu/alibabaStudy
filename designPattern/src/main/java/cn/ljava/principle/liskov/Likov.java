package cn.ljava.principle.liskov;

/***
 * 里氏替换原则
 */
public class Likov {
    public static void main(String[] args) {

    }
}
class A {
    public int func1(int num1, int num2){
        return num1 - num2;
    }
}
class B extends A{
    //B无意重写了A中的方法
    public int func1(int num1, int num2){
        return num1 + num2;
    }
    public int func2(int num1, int num2){
        return func1(num1, num2) + 2;
    }
}
