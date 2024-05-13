package cn.ljava.adapterPattern;

public abstract class InterfaceAdapter implements VGA {

    USB usb = new USBImpl();
    @Override
    public void vgaMethod()
    {
        //System.out.println("VGA接口方法");
        usb.usbMethod();
    }

    @Override
    public void a()
    {

    }

    @Override
    public void b()
    {

    }
}
