package com.bw.shopcar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：ww
 * <p>
 * 邮箱：2973627661@qq.com
 * <p>
 * 时间:on 2019/4/4 14:50
 */
public class WaterLine extends View {

    private Paint paint;
    private Path path;
    private float mX;
    private AnimationListener animationListener;

    public WaterLine(Context context) {
        super(context);
        init(context);
    }

    public WaterLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //画笔 和 路径
        paint = new Paint();
        path = new Path();

        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //重置
        path.reset();
        //开始位置
        path.moveTo(getLeft(),getBottom());

        //波动位置
        float mPi= (float) (Math.PI*2/getRight());

        mX=mX-0.1f;
        for (int x=0;x<=getRight();x+=20){
            float y = (float) (10 * Math.cos(mPi * x + mX) + 10);
            path.lineTo(x,y);
            if (animationListener!=null){
                animationListener.Animation(y);
            }
        }
        //结束位置
        path.lineTo(getRight(),getBottom());
        canvas.drawPath(path,paint);
        postInvalidateDelayed(20);
    }
    public void setAnimationListener(AnimationListener animationListener){
        this.animationListener=animationListener;
    }

    public interface AnimationListener{
        void Animation(float y);
    }
}
