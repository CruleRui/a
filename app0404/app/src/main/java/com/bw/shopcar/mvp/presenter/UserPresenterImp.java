package com.bw.shopcar.mvp.presenter;

import com.bw.shopcar.carBean.CarData;
import com.bw.shopcar.mvp.model.UserModel;
import com.bw.shopcar.mvp.view.UserView;

import java.util.ArrayList;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 15:33
 */
public class UserPresenterImp implements UserPresenter, UserModel.CallBackListener {
    private UserModel userModel;
    private UserView userView;

    public UserPresenterImp(UserModel userModel, UserView userView) {
        this.userModel = userModel;
        this.userView = userView;
    }

    @Override
    public void doLogin(int type, String phone, String pwd) {
        userModel.doLogin(type,phone,pwd,this);
    }

    @Override
    public void doRegis(int type, String phone, String pwd) {

        userModel.doRegis(type,phone,pwd,this);
    }

    @Override
    public void doShopCar(int type) {
        userModel.doShopCar(type,this);
    }

    @Override
    public void success(int type, String data) {
        userView.success(type,data);
    }

    @Override
    public void fail(int type, String error) {
        userView.fail(type,error);
    }

    public void destory(){
        if (userModel!=null){
            userModel=null;
        }else if(userView!=null){
            userView=null;
        }
        System.gc();
    }
    
}
