package com.wy.younger.android.framework.vm;

import java.io.*;

/**
 * @package:com.wy.younger.framework.vm
 * @data on:2019/2/13 11:18
 * author:YOUNG
 * desc:TODO
 */
public class DiskClassLoader extends ClassLoader {
    private String path;

    public DiskClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        byte[] classData = loadClassData(name);//1
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            clazz = defineClass(name, classData, 0, classData.length);//2 将class文件的字节码数组转为Class类的实例
        }
        return clazz;
    }


    private byte[] loadClassData(String name) {
        String fileName = getFileName(name);
        File file = new File(path, fileName);
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if (index == -1) {//如果没有找到'.'则直接在末尾添加.class
            return name + ".class";
        } else {
            return name.substring(index + 1) + ".class";
        }
    }
}
