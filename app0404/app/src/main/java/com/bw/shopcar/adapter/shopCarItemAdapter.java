package com.bw.shopcar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.shopcar.R;
import com.bw.shopcar.carBean.CarList;
import com.bw.shopcar.view.AddView;

import java.util.ArrayList;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/5 16:58
 */
public class shopCarItemAdapter extends RecyclerView.Adapter<shopCarItemAdapter.ShopCarItemViewHolder> {
    private ArrayList<CarList> carLists = new ArrayList<>();
    private Context context;
    private ItemListener itemListener;

    public shopCarItemAdapter(ArrayList<CarList> carLists, Context context) {
        this.carLists = carLists;
        this.context = context;
    }

    @NonNull
    @Override
    public shopCarItemAdapter.ShopCarItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_adapter, null);
        ShopCarItemViewHolder itemViewHolder = new ShopCarItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final shopCarItemAdapter.ShopCarItemViewHolder shopCarItemViewHolder, final int i) {

        shopCarItemViewHolder.item_checkbox.setChecked(carLists.get(i).isChecked());
        shopCarItemViewHolder.item_title.setText(carLists.get(i).getTitle());
        shopCarItemViewHolder.item_price.setText(carLists.get(i).getPrice() + "");
        Glide.with(context).load(carLists.get(i).getImages()).into(shopCarItemViewHolder.item_img);

        shopCarItemViewHolder.add_view.setNum(carLists.get(i).getNum());

        //商品的checkbox
        shopCarItemViewHolder.item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = shopCarItemViewHolder.item_checkbox.isChecked();
                carLists.get(i).setChecked(checked);
                if (itemListener != null) {
                    itemListener.changeData(carLists);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carLists.size();
    }

    public class ShopCarItemViewHolder extends RecyclerView.ViewHolder {

        private CheckBox item_checkbox;
        private ImageView item_img;
        private TextView item_title;
        private TextView item_price;
        private AddView add_view;

        public ShopCarItemViewHolder(@NonNull View itemView) {
            super(itemView);
            item_checkbox = itemView.findViewById(R.id.item_checkbox);
            item_img = itemView.findViewById(R.id.item_img);
            item_title = itemView.findViewById(R.id.item_title);
            item_price = itemView.findViewById(R.id.item_price);
            add_view = itemView.findViewById(R.id.add_view);
        }
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener {
        void changeData(ArrayList<CarList> carLists);
    }
}
