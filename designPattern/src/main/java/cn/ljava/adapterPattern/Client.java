package cn.ljava.adapterPattern;

public class Client {
    public static void main(String[] args) {
        //对象适配器
       // objectAdapterTest();
        //类适配器
        //classAdapterTest();
        interfaceAdapterTest();
    }

    /**
     * 对象适配器使用组合
     */
    private static void objectAdapterTest() {
        USB usb = new USBImpl();
        //创建适配器对象,并将适配者对象传入适配器中
        ObjectAdapter adapter = new ObjectAdapter(usb);

        //调用vga(目标接口)的方法,实际上会委托给被适配者的方法执行
        adapter.vgaMethod();
    }

    /**
     *
     */
    private static void classAdapterTest(){
        //通过适配器创建个VGA对象,这个适配器实际是使用的USB的usbMethod()方法
        VGA vga = new ClassAdapter();
        //通过Projector将USB对象映射成VGA对象, 调用projector的projection方法,实际上会调用VGA的vgaMethod()方法
        Projector projector = new Projector();
        projector.projection(vga);
    }

    /**
     * 接口适配器, InterfaceAdapterImpl不用实现b()和c()方法
     */
    private static void interfaceAdapterTest(){
        InterfaceAdapter adapter = new InterfaceAdapterImpl();
        adapter.vgaMethod();
    }
}
