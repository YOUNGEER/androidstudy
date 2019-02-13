package com.wy.younger.framework.vm

import java.lang.reflect.InvocationTargetException

/**
 *@package:com.wy.younger.framework.vm
 *@data on:2019/2/13 10:20
 *author:YOUNG
 *desc:TODO
 */

class Main {

}


fun main() {
    var loader = Main::class.java.classLoader
    while (loader != null) {
        System.out.println(loader)
        loader = loader.parent
    }


    val diskClassLoader =
        DiskClassLoader("D:\\project\\younger\\app\\src\\main\\java\\com\\wy\\younger\\framework\\vm")//1
    try {
        val c = diskClassLoader.loadClass("com.wy.younger.framework.vm.Job")//2
        if (c != null) {
            try {
                val obj = c.newInstance()
                println(obj.javaClass.classLoader)
                val method = c.getDeclaredMethod("say", String::class.java)
                method.invoke(obj, "hello")//3
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: SecurityException) {
                e.printStackTrace()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }

        }
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }


}