package com.bw.shopcar.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bw.shopcar.R;
import com.bw.shopcar.adapter.FragmentAdapter;
import com.bw.shopcar.fragment.MyFragment;
import com.bw.shopcar.fragment.ShouFragment;
import com.bw.shopcar.view.PagerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PagerView view_pager;
    private RadioGroup radio_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        view_pager = findViewById(R.id.view_pager);
        radio_group = findViewById(R.id.radio_group);

        //创建fragment集合
        ArrayList<Fragment> list=new ArrayList<>();
        list.add(new ShouFragment());
        list.add(new MyFragment());

        //创建适配器
        FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager(),list);
        //设置适配器
        view_pager.setAdapter(adapter);

        //点击切换
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                   case  R.id.btn1:
                       view_pager.setCurrentItem(0,false);
                       break;
                    case R.id.btn2:
                        view_pager.setCurrentItem(1,false);
                        break;
                }
            }
        });
    }
}
