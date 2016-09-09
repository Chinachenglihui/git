package com.example.administrator.first_porject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.first_porject.Util.SpUtil;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class SetupOverActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        boolean setup_over = SpUtil.getBoolean(this,ConstanValue.SETUP_OVER,false);
//        if (setup_over){
//            setContentView(R.layout.activity_setup1);
//        }else{
//
//        }
        setContentView(R.layout.activity_setup1);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_next){
            Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(intent);
        }
    }
}
