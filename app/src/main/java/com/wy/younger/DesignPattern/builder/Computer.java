package com.wy.younger.DesignPattern.builder;

import android.graphics.Color;
import android.text.TextUtils;

public class Computer {
    private String cup;
    private String ram;
    private int color;
    private String weight;
    private String disk;
    private String power;


    public static class Builder {
        public String cup;
        public String ram;
        public int color;
        public String weight;
        public String disk;
        public String power;


        public Builder setCup(String cup) {
            this.cup = cup;
            return this;
        }


        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }


        public Builder setColor(String color) {
            this.color = color;
            return this;
        }


        public Builder setWeight(String weight) {
            this.weight = weight;
            return this;
        }


        public Builder setDisk(String disk) {
            this.disk = disk;
            return this;
        }


        public Builder setPower(String power) {
            this.power = power;
            return this;
        }

        //默认实现
        public Computer create() {
            if (TextUtils.isEmpty(cup)) {
                cup = "8G";
            }
            if (TextUtils.isEmpty(disk)) {
                disk = "1T";
            }
            if (TextUtils.isEmpty(power)) {
                power = "200w";
            }
            if (TextUtils.isEmpty(weight)) {
                weight = "360g";
            }
            if (TextUtils.isEmpty(String.valueOf(Color.RED))) {
                color = "Color.RED";
            }

            Computer computer = new Computer();
            computer.color = color;
            computer.cup = cup;
            computer.disk = disk;
            computer.power = power;
            computer.weight = weight;

            return computer;
        }

    }

}
