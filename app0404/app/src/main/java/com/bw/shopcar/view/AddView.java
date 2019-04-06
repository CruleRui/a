package com.bw.shopcar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bw.shopcar.R;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 17:10
 */
public class AddView extends RelativeLayout {

    private EditText edit_text;

    public AddView(Context context) {
        super(context);

    }

    public AddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view=View.inflate(context, R.layout.add_layout,null);
        edit_text = view.findViewById(R.id.edit_text);
        addView(view);
    }

    public void setNum(int num) {
        //设置数量
        edit_text.setText(num+"");
    }
}
