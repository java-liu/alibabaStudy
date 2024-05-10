package cn.ljava.adapterPattern;

/**
 *--------------------------
 * 适配器,用于将USB接口转换成客户端所期望的VGA接口.
 * 使用原本USB接口也能完成VGA接口的工作.
 * 允许不兼容的接口之间进行合作,使得原本接口不匹配而无法工作的类能够协同工作.
 *
 * 实现VGA接口,表示适配器类是VGA类型的,适配器方法中直接ust对象
 *--------------------------
 * @author Liuys (开发人员)
 * @date 2024/5/10 (开发时间)
 * @version v1.0 (版本号)
 *-------------------------
*/
public class ObjectAdapter implements VGA{

    private USB usb;

    public ObjectAdapter(USB usb) {
        this.usb = usb;
    }
    @Override
    public void vgaMethod() {
        usb.usbMethod();
    }
}
