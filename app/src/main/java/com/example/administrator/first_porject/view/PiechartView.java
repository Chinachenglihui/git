package com.example.administrator.first_porject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.first_porject.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class PiechartView extends View {
    private Paint paint;
    private RectF oval;
    private float proportionPhone = 0;
    private float proportionSD = 0;
    private float piecharAnglePhone = 0;
    private float piecharAngleSD = 0;
    private int phoneColor = 0;
    private int sdColor = 0;
    private int baseColor = 0;
    public PiechartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        phoneColor = context.getResources().getColor(R.color.piechar_phone);
        sdColor = context.getResources().getColor(R.color.piechar_sdcard);
        baseColor = context.getResources().getColor(R.color.piechar_base);

    }
    /**
     * 设置饼形图(在整个手机存储空间中)空间大小比例 0.0f -- 1.0f
     *
     * @param f1
     *            手机
     * @param f2
     *            SDCard
     */
    public void setPiechartProportionWithAnim(float f1, float f2) {
        proportionPhone = f1;
        proportionSD = f2;
        // 目标角度
        final float phoneTargetAngle = 360 * proportionPhone;
        final float sdcardTargetAngle = 360 * proportionSD;
// 通过每次+4度，"通过动画"设定到目标角度
        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                piecharAnglePhone += 4;
                piecharAngleSD += 4;
                postInvalidate();
                if (piecharAnglePhone >= phoneTargetAngle) {
                    piecharAnglePhone = phoneTargetAngle;
                }
                if (piecharAngleSD >= sdcardTargetAngle) {
                    piecharAngleSD = sdcardTargetAngle;
                }
                if (piecharAnglePhone == phoneTargetAngle && piecharAngleSD == sdcardTargetAngle) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 26, 26);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //根据父控件传入的宽高规范，获取控件的宽高
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        //Create a new rectangle with the specified coordinates
        oval = new RectF(0, 0, viewWidth, viewHeight);
        //存储控件测量的的宽高值
        setMeasuredDimension(viewWidth, viewHeight);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setAntiAlias(true);
        // 绘制底色
        paint.setColor(baseColor);
        canvas.drawArc(oval, -90, 360, true, paint);
        // 绘制手机空间所占效果
        paint.setColor(phoneColor);
        canvas.drawArc(oval, -90, piecharAnglePhone, true, paint);
        // 绘制ＳＤ空间所占效果
        paint.setColor(sdColor);
        canvas.drawArc(oval,  -90 + piecharAnglePhone, piecharAngleSD, true,
                paint);
    }
}
