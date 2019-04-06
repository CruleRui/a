package com.bw.shopcar.mvp.model;

import com.bw.shopcar.net.OkHttpUtil;

import okhttp3.FormBody;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 15:31
 */
public class UserModelImp implements UserModel {
    @Override
    public void doLogin(final int type, String phone, String pwd, final CallBackListener backListener) {
        String loginUrl="http://172.17.8.100/small/user/v1/login";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone",phone);
        builder.add("pwd",pwd);
        new OkHttpUtil().post(loginUrl,builder).result(new OkHttpUtil.HttpUtilListener() {
            @Override
            public void success(String data) {
                backListener.success(type,data);
            }

            @Override
            public void fail(String error) {
                backListener.fail(type,error);
            }
        });
    }

    @Override
    public void doRegis(final int type, String phone, String pwd, final CallBackListener backListener) {
        String regisUrl="http://172.17.8.100/small/user/v1/register";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone",phone);
        builder.add("pwd",pwd);
        new OkHttpUtil().post(regisUrl,builder).result(new OkHttpUtil.HttpUtilListener() {
            @Override
            public void success(String data) {
                backListener.success(type,data);
            }

            @Override
            public void fail(String error) {
                backListener.fail(type,error);
            }
        });
    }

    @Override
    public void doShopCar(final int type, final CallBackListener backListener) {
        String shopUrl="http://172.17.8.100/ks/product/getCarts?uid=51";
        new OkHttpUtil().get(shopUrl).result(new OkHttpUtil.HttpUtilListener() {
            @Override
            public void success(String data) {
                backListener.success(type,data);
            }

            @Override
            public void fail(String error) {
                backListener.fail(type,error);
            }
        });
    }
}
