package com.bw.shopcar.carBean;

import java.util.ArrayList;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 16:55
 */
public class Shop {
    private ArrayList<CarData> data;

    public ArrayList<CarData> getData() {
        return data;
    }

    public void setData(ArrayList<CarData> data) {
        this.data = data;
    }

    public Shop(ArrayList<CarData> data) {
        this.data = data;
    }

    public Shop() {
    }

    @Override
    public String toString() {
        return "Shop{" +
                "data=" + data +
                '}';
    }
}
