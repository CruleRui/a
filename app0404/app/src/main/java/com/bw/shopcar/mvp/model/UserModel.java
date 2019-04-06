package com.bw.shopcar.mvp.model;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 15:29
 */
public interface UserModel {
    interface CallBackListener{
        void success(int type,String data);
        void fail(int type,String error);
    }
    void doLogin(int type,String phone,String pwd,CallBackListener backListener);
    void doRegis(int type,String phone,String pwd,CallBackListener backListener);
    void doShopCar(int type,CallBackListener backListener);
}
