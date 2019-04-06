package com.bw.shopcar.carBean;

import java.util.ArrayList;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 16:56
 */
public class CarData {
    private ArrayList<CarList> list;
    private String sellerName;
    private String sellerid;
    private boolean checked;

    public ArrayList<CarList> getList() {
        return list;
    }

    public void setList(ArrayList<CarList> list) {
        this.list = list;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public CarData(ArrayList<CarList> list, String sellerName, String sellerid, boolean checked) {
        this.list = list;
        this.sellerName = sellerName;
        this.sellerid = sellerid;
        this.checked = checked;
    }

    public CarData() {
    }

    @Override
    public String toString() {
        return "CarData{" +
                "list=" + list +
                ", sellerName='" + sellerName + '\'' +
                ", sellerid='" + sellerid + '\'' +
                ", checked=" + checked +
                '}';
    }
}
