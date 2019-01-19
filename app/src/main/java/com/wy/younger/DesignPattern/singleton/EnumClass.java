package com.wy.younger.DesignPattern.singleton;

/**
 * Created by jingbin on 2016/10/28.
 * 枚举[极推荐使用]
 * <p>
 * EnumClass.instance
 * 这里的instance即为SingletonEnum类型的引用所以得到它就可以调用枚举中的方法了。
 * 借助JDK1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 * jvm层面保证单例安全
 */
public enum EnumClass {
    instance,


}