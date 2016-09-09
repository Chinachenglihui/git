package com.example.administrator.first_porject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.first_porject.Util.SpUtil;
import com.example.administrator.first_porject.view.SettingItemView;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class SettingActivity extends AppCompatActivity{
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        //设置支持toolbar

        setSupportActionBar(toolbar);
        //支持图标导航
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        assert toolbar!=null;
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        initUpdate();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    private void initUpdate() {
        final SettingItemView siv_update= (SettingItemView) findViewById(R.id.siv_update);
        //获取开关状态，用作显示
        boolean open_update= SpUtil.getBoolean(this,ConstanValue.OPEN_UPDATE,false);
        //是否选中，根据上一次存储的结果去做决定
        siv_update.setCheck(open_update);
        siv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取之前的选中状态
                boolean isCheck=siv_update.isCheck();
                //将原有状态取反
                siv_update.setCheck(!isCheck);
                //将取反后的状态存储到相应的sp中
                SpUtil.putBoolean(getApplicationContext(),ConstanValue.OPEN_UPDATE,!isCheck);


            }
        });
    }

}
