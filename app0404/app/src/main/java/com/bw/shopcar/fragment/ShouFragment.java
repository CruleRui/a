package com.bw.shopcar.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.shopcar.R;
import com.bw.shopcar.adapter.ShouAdapter;
import com.bw.shopcar.carBean.CarData;
import com.bw.shopcar.carBean.CarList;
import com.bw.shopcar.carBean.Shop;
import com.bw.shopcar.mvp.model.UserModelImp;
import com.bw.shopcar.mvp.presenter.UserPresenterImp;
import com.bw.shopcar.mvp.view.UserView;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 14:37
 */
public class ShouFragment extends Fragment implements UserView {

    private RecyclerView shou_recycle;
    private CheckBox shou_checkbox_all;
    private TextView tv_hj;
    private TextView tv_js;
    private UserPresenterImp userPresenterImp;
    private ShouAdapter shouAdapter;
    private ArrayList<CarData> carList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.shou_fragment, null);

        //初始化控件
        shou_recycle = view.findViewById(R.id.shou_recycle);
        shou_checkbox_all = view.findViewById(R.id.shou_checkbox_all);
        tv_hj = view.findViewById(R.id.tv_hj);
        tv_js = view.findViewById(R.id.tv_js);

        userPresenterImp = new UserPresenterImp(new UserModelImp(), this);
        userPresenterImp.doShopCar(1);

        //创建适配器
        shouAdapter = new ShouAdapter(getActivity());
        //设置适配器
        shou_recycle.setAdapter(shouAdapter);


        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shou_recycle.setLayoutManager(linearLayoutManager);

        //全选的复选点击事件
        shou_checkbox_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = shou_checkbox_all.isChecked();

                double allPrice=0;
                int allNum=0;

                for (int i = 0; i < carList.size(); i++) {
                    carList.get(i).setChecked(checked);//商家是否选中
                    ArrayList<CarList> shop_list = carList.get(i).getList();//商家下对应的商品

                    for (int j = 0; j < shop_list.size(); j++) {
                        shop_list.get(j).setChecked(checked);
                        if (checked){//全选
                            double price = shop_list.get(j).getPrice();
                            int num = shop_list.get(j).getNum();
                            allPrice = allPrice+price*num;
                            allNum=allNum+num;
                        }else{//反选
                            allPrice=0;
                            allNum=0;
                        }
                    }
                }
                tv_hj.setText(allPrice+"");
                tv_js.setText("去结算（"+allNum+")");
                //刷新适配器
                shouAdapter.setList(carList);
            }
        });

        shouAdapter.setOnCallBackListerner(new ShouAdapter.OnCallBackListerner() {
            @Override
            public void changeData(ArrayList<CarData> list) {
                double allPrice=0;
                int allNum=0;

                for (int i=0;i<list.size();i++){
                    boolean checked = list.get(i).isChecked();
                    if (checked){
                        ArrayList<CarList> listChange = list.get(i).getList();
                        for (int j=0;j<listChange.size();j++){
                            double price = listChange.get(j).getPrice();
                            int num = listChange.get(j).getNum();
                            allPrice=allPrice+price*num;
                            allNum=allNum+num;
                        }
                    }else{//商家未选中，商品选中了
                        ArrayList<CarList> listChange = list.get(i).getList();
                        for (int j=0;j<listChange.size();j++){
                            if (listChange.get(j).isChecked()){
                                double price = listChange.get(j).getPrice();
                                int num = listChange.get(j).getNum();
                                allPrice=allPrice+price*num;
                                allNum=allNum+num;
                            }
                        }
                    }
                }
                tv_hj.setText(allPrice+"");
                tv_js.setText("去结算:（"+allNum+")");
            }
        });
        return view;
    }

    @Override
    public void success(int type, String data) {
        Gson gson = new Gson();
        Shop shop = gson.fromJson(data, Shop.class);
        carList = shop.getData();

        carList.remove(0);

        shouAdapter.setList(carList);
    }

    @Override
    public void fail(int type, String error) {

    }
}
