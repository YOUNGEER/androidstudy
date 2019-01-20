package com.wy.younger.DesignPattern.factory;

import android.graphics.BitmapFactory;

public class BitmapFactoryDemo {

    private void getBitmap(String filePath) {
        BitmapFactory.decodeFile(filePath);
    }

}
