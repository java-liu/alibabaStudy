package cn.ljava.principle.inversion;

public class DependecyInversion3 {
    public static void main(String[] args) {
        ChangHong changHong = new ChangHong();
        //OpenAndClose openAndClose = new OpenAndClose();
        //遥控器把长虹电视打开了
        //openAndClose.open(changHong);

        //方式二,通过构造器方式依赖 依赖=使用
        //OpenAndClose openAndClose = new OpenAndClose(changHong);
        //遥控器把长虹电视打开了
        //openAndClose.open();

        //方式三，通过setter方式依赖，
        OpenAndClose openAndClose = new OpenAndClose();
        //遥控器拿到电视，就可以操作了
        openAndClose.setTv(changHong);
        openAndClose.open();

    }
}
//三种方式实现依赖倒置
//方式一，通过接口传递实现依赖
//开关的接口
/*interface IOpenAndClose{
    //遥控器有开关的功能,去操纵电视
    void open(ITv tv);
}
interface  ITv{
    //电视有play功能
    void play();
}
class OpenAndClose implements IOpenAndClose{
    public void open(ITv tv){
        tv.play();
    }
}*/
//方式二，通过构造方法依赖传递
/*interface IOpenAndClose{
    //遥控器有开关的功能,去操纵电视
    void open();
}
interface  ITv{
    //电视有play功能
    void play();
}
class OpenAndClose implements IOpenAndClose{
    public ITv tv; //成员
    public OpenAndClose(ITv tv){
        this.tv = tv;
    }
    public void open(){
        tv.play();
    }
}*/

//方式三，通过setter方式依赖
interface IOpenAndClose{
    //遥控器有开关的功能,去操纵电视
    void open();
}
interface  ITv{
    //电视有play功能
    void play();
}
class OpenAndClose implements IOpenAndClose{
    public ITv tv; //成员

    public void setTv(ITv tv) {
        this.tv = tv;
    }

    public void open(){
        this.tv.play();
    }
}
class ChangHong implements ITv{

    @Override
    public void play() {
        System.out.println("长虹电视，打开了");
    }
}
