package com.bw.shopcar.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.shopcar.activity.LoginActivity;
import com.bw.shopcar.R;
import com.bw.shopcar.view.WaterLine;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 14:37
 */
public class MyFragment extends Fragment {

    private ImageView login_img;
    private TextView login_name;
    private WaterLine waterline;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.my_fragment,null);
        login_img = view.findViewById(R.id.login_img);
        login_name = view.findViewById(R.id.login_name);
        waterline = view.findViewById(R.id.waterline);

        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String pic = sp.getString("pic", null);
        String name = sp.getString("name", null);
        Glide.with(getActivity()).load(pic).apply(new RequestOptions().circleCrop()).into(login_img);
        login_name.setText(name);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        waterline.setAnimationListener(new WaterLine.AnimationListener() {
            @Override
            public void Animation(float y) {
                //Log.d("flag",y+"");
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) login_img.getLayoutParams();
                params.setMargins(0, (int) y,0,0);
                login_img.setLayoutParams(params);
            }
        });

        //点击头像跳转到登陆页面
        login_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
