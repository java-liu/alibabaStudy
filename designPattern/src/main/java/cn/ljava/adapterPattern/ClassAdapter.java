package cn.ljava.adapterPattern;

/**
 *--------------------------
 * 类适配器,继承USBImpl,获取USB的功能,其次,实现VGA接口,表示该类的类型为VGA.
 *--------------------------
 * @author Liuys (开发人员)
 * @date 2024/5/10 (开发时间)
 * @version v1.0 (版本号)
 *-------------------------
*/
public class ClassAdapter extends USBImpl implements VGA{
    @Override
    public void vgaMethod() {
        super.usbMethod();
    }
}
