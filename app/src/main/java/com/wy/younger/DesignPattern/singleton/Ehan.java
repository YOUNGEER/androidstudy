package com.wy.younger.DesignPattern.singleton;

/**
 * 饿汉式：不管你什么时候调用该对象，我先创建了再说
 * <p>
 * 优点：从它的实现中我们可以看到，这种方式的实现比较简单，在类加载的时候就完成了实例化，避免了线程的同步问题。
 * 缺点：由于在类加载的时候就实例化了，所以没有达到Lazy Loading(懒加载)的效果，也就是说可能我没有用到这个实例，但是它
 * 也会加载，会造成内存的浪费(但是这个浪费可以忽略，所以这种方式也是推荐使用的)。
 */
public class Ehan {


    private static Ehan instance1 = new Ehan();

    private static Ehan getInstance1() {
        return instance1;
    }


    private static Ehan instance2 = null;

    static {
        instance2 = new Ehan();
    }

    private static Ehan getInstance2() {
        if (null == instance2) {
            instance2 = new Ehan();
        }
        return instance2;
    }


}



