package com.bw.shopcar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.shopcar.R;
import com.bw.shopcar.carBean.CarData;
import com.bw.shopcar.carBean.CarList;

import java.util.ArrayList;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 17:02
 */
public class ShouAdapter extends RecyclerView.Adapter<ShouAdapter.CarViewHolder> {
    private Context context;
    private ArrayList<CarData> list = new ArrayList<>();

    public ShouAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ShouAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.shou_adapter, null);
        CarViewHolder carViewHolder = new CarViewHolder(view);
        return carViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShouAdapter.CarViewHolder carViewHolder, final int i) {
        carViewHolder.shop_home.setText(list.get(i).getSellerName());

        carViewHolder.car_check_box.setChecked(list.get(i).isChecked());

        final ArrayList<CarList> carLists = this.list.get(i).getList();

        //创建适配器
        shopCarItemAdapter adapter = new shopCarItemAdapter(carLists, context);

        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        carViewHolder.shou_child_recycle.setLayoutManager(linearLayoutManager);

        //设置适配器
        carViewHolder.shou_child_recycle.setAdapter(adapter);

        adapter.setItemListener(new shopCarItemAdapter.ItemListener() {
            @Override
            public void changeData(ArrayList<CarList> carLists) {

                int checkNum=0;

                for (int i = 0; i < carLists.size(); i++) {
                    boolean checked = carLists.get(i).isChecked();
                    if (checked){
                        checkNum++;
                    }
                }
                if (checkNum==carLists.size()){//商家下的商品全选
                    carViewHolder.car_check_box.setChecked(true);
                }else{
                    carViewHolder.car_check_box.setChecked(false);
                }
                if (listerner!=null){
                    listerner.changeData(list);//回传给调用页面
                }
            }
        });
        //商家选中的点击事件
        carViewHolder.car_check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = carViewHolder.car_check_box.isChecked();

                list.get(i).setChecked(checked);

                ArrayList<CarList> carLists = list.get(i).getList();
                for (int i = 0; i < carLists.size(); i++) {
                    carLists.get(i).setChecked(checked);//设置是否选中
                }

                if (listerner!=null){
                    listerner.changeData(list);//回传给调用页面
                }
                //刷新适配器
                notifyItemChanged(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<CarData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        private TextView shop_home;
        private CheckBox car_check_box;
        private RecyclerView shou_child_recycle;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            shop_home = itemView.findViewById(R.id.shop_home);
            car_check_box = itemView.findViewById(R.id.car_check_box);
            shou_child_recycle = itemView.findViewById(R.id.shou_child_recycle);
        }
    }

    private OnCallBackListerner listerner;
    public void setOnCallBackListerner(OnCallBackListerner listerner){
        this.listerner=listerner;
    }
    public interface OnCallBackListerner{
        void changeData(ArrayList<CarData> list);
    }
}
