package com.example.administrator.first_porject.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import android.view.animation.AlphaAnimation;


import android.widget.ImageView;

import com.example.administrator.first_porject.R;


/**
 * Created by Administrator on 2016/8/16 0016.
 */
public class BackgroundActivity extends Activity {
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg){
            finish();
        };
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yan);
        ImageView iv_bottom= (ImageView) findViewById(R.id.iv_bottom);
        ImageView iv_top= (ImageView) findViewById(R.id.iv_top);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(500);
        assert iv_top != null;
        iv_top.startAnimation(alphaAnimation);
        assert iv_bottom != null;
        iv_bottom.startAnimation(alphaAnimation);
        mHandler.sendEmptyMessageDelayed(0,500);
    }
}
