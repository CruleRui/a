package com.bw.shopcar.mvp.view;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 15:28
 */
public interface UserView {
    void success(int type,String data);
    void fail(int type,String error);
}
