package cn.ljava.adapterPattern;

/**
 *--------------------------
 * 将USB映射为VGA, 只有VGA接口才可以使用vgaMethod
 *--------------------------
 * @author Liuys (开发人员)
 * @date 2024/5/10 (开发时间)
 * @version v1.0 (版本号)
 *-------------------------
*/
public class Projector<T> {
    public void projection(T t){
        if(t instanceof VGA){
            System.out.println("it is vga,it can use vga method");
            VGA vga = (VGA) t;
            vga.vgaMethod();
        }else{
            System.out.println("it is not vga,it can not use vga method");
        }
    }
}
