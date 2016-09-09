package com.example.rocketman;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class RocketService extends Service {
    private WindowManager mWM;
    private int mScreenHeight;
    private int mScreenWidth;
    private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
    private View mRocketView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage( Message msg) {
            params.y=(Integer) msg.obj;
            //告知窗体所在位置
            mWM.updateViewLayout(mRocketView,params);
        }
    };
    private WindowManager.LayoutParams params;
    private int startX;
    private int startY;


    @Override
    public void onCreate() {
        //获取窗体对象
        mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
        mScreenHeight = mWM.getDefaultDisplay().getHeight();
        mScreenWidth = mWM.getDefaultDisplay().getWidth();
        //开启火箭
        showRocket();
        super.onCreate();
    }

    private void showRocket() {
        //自定义吐司
        params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.format = PixelFormat.TRANSLUCENT;
        //响铃时显示土司和电话类型一致
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.setTitle("Toast");
        //指定土司所在位置
        params.gravity = Gravity.LEFT + Gravity.TOP;
        //指定土司所在布局，并且将其转化为view，添加窗体权限
        mRocketView = View.inflate(this, R.layout.rocket_view, null);
        final ImageView iv_rocket = (ImageView) mRocketView.findViewById(R.id.id_rocket);
        iv_rocket.post(new Runnable() {
            @Override
            public void run() {
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_rocket.getBackground();
        animationDrawable.start();

            }
        });
        mWM.addView(mRocketView, params);
        mRocketView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int moveX = (int) event.getRawX();
                        int moveY = (int) event.getRawY();
                        int disX = moveX - startX;
                        int disY = moveY - startY;
                        params.x = params.x + disX;
                        params.y = params.y + disY;
                        if (params.x < 0) {
                            params.x = 0;
                        }
                        if (params.y < 0) {
                            params.y = 0;
                        }
                        if (params.x > mScreenWidth - mRocketView.getWidth()) {
                            params.x = mScreenWidth - mRocketView.getWidth();
                        }
                        if (params.y > mScreenHeight - mRocketView.getHeight() - 22) {
                            params.y = mScreenHeight - mRocketView.getHeight() - 22;
                        }
                        mWM.updateViewLayout(mRocketView, params);
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (params.x > 100 && params.x < 200 && params.y > 350) {
                            //发射火箭
                             sendRocket();
                            //开启产生尾气的Activity
                            Intent intent=new Intent(getApplicationContext(),BackgroundActivity.class);
                            //开启火箭后，关闭唯一的Activity对应的任务栈，所以在此次需要告知新开启的activity开辟一个新的任务栈
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        break;
                }
                return true;
            }

            private void sendRocket() {
                new Thread() {
                    //向上过程中Y一直减少，直到0
                    //主线程中不能去睡眠，可能导致主线程阻塞
                    public void run() {
                        for (int i = 0; i < 11; i++) {
                            int height = 350 - i * 35;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                             Message msg=Message.obtain();
                            msg.obj=height;
                            mHandler.sendMessage(msg);
                        }

                    }
                }.start();
            }
        });
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        if (mWM != null && mRocketView != null) {
            mWM.removeView(mRocketView);
        }
        super.onDestroy();
    }
}
