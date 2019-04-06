package com.bw.shopcar.mvp.presenter;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 15:31
 */
public interface UserPresenter {
    void doLogin(int type,String phone,String pwd);
    void doRegis(int type,String phone,String pwd);
    void doShopCar(int type);
}
