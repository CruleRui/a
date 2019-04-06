package com.bw.shopcar.carBean;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 16:57
 */
public class CarList {
    private String images;
    private String title;
    private double price;
    private int num;
    private boolean checked;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public CarList(String images, String title, double price, int num, boolean checked) {
        this.images = images;
        this.title = title;
        this.price = price;
        this.num = num;
        this.checked = checked;
    }

    public CarList() {
    }

    @Override
    public String toString() {
        return "CarList{" +
                "images='" + images + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", checked=" + checked +
                '}';
    }
}
