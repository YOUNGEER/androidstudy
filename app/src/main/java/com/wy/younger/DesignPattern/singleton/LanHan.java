package com.wy.younger.DesignPattern.singleton;

/**
 * 懒汉式：需要的时候才会创建对象
 */
public class LanHan {


    /**
     * 单例模式的懒汉式[线程不安全，不可用]
     */
    private static LanHan instance1 = null;

    private static LanHan getInstance1() {
        if (null == instance1) {
            instance1 = new LanHan()
        }
        return instance1;
    }


    /**
     * 懒汉式线程安全的:[线程安全，效率低不推荐使用]
     * <p>
     * 缺点：效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。
     * 而其实这个方法只执行一次实例化代码就够了，
     * 后面的想获得该类实例，直接return就行了。方法进行同步效率太低要改进。
     */
    private static LanHan instance2 = null;

    private static synchronized LanHan getInstance2() {
        if (null == instance2) {
            instance2 = new LanHan();
        }
        return instance2;
    }


    /**
     * 单例模式懒汉式[线程不安全，不可用]
     * <p>
     * 虽然加了锁，但是等到第一个线程执行完instance=new Singleton()跳出这个锁时，
     * 另一个进入if语句的线程同样会实例化另外一个Singleton对象，线程不安全的原理跟3类似。
     */
    private static LanHan instance3 = null;

    private static LanHan getInstance3() {
        if (null == instance3) {
            synchronized (LanHan.class) {
                instance3 = new LanHan()
            }
        }
        return instance3;
    }


    /**
     * 单例模式懒汉式双重校验锁[推荐用]
     * 懒汉式变种,属于懒汉式的最好写法,保证了:延迟加载和线程安全
     */
    private static LanHan instance4 = null;

    private static LanHan getInstance4() {
        if (null == instance4) {
            synchronized (LanHan.class) {
                if (null == instance4) {
                    instance4 = new LanHan()
                }
            }
        }
        return instance4;
    }
}
