package cn.ljava.principle.inversion;

/***
 * 方式二
 */
public class DependencyInversionImprove {
    public static void main(String[] args) {
        Person2 person = new Person2();
        person.receive(new Email2());
        person.receive(new Weixin());
    }
}
interface IReceiver{
    String getInfo();
}
class Email2 implements IReceiver{

    @Override
    public String getInfo() {
        return "电子邮件信息：hello,world";
    }
}
class Weixin implements IReceiver{
    @Override
    public String getInfo() {
        return "微信信息：hello,world";
    }
}
//方式二
class Person2{
    public void receive(IReceiver receiver){
        System.out.println(receiver.getInfo());
    }
}