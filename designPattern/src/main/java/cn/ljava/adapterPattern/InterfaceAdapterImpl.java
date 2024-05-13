package cn.ljava.adapterPattern;

public class InterfaceAdapterImpl extends InterfaceAdapter{
    public void vgaMethod()
    {
        System.out.println("InterfaceAdapterImpl.vgaMethod()");
        super.vgaMethod();
    }
}
