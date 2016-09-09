package com.example.administrator.first_porject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.first_porject.ui.HomeActivity;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class SplashActivity extends Activity {
     static final int ENTER_HOME = 101;
     Handler mHandler=new Handler(){
        //自定义方法
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ENTER_HOME:
                    enterHome();
                    break;
            }
        };
    };

    void enterHome() {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private TextView tv_version_name;
    private int mLocalVersionCode;
    private RelativeLayout rl_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initUI();
        initData();
        initAnimation();
    }
    //添加淡入动画
    private  void initAnimation(){
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(3000);
        rl_root.startAnimation(alphaAnimation);
    }
    private void initData() {
        //1，应用版本名称
        tv_version_name.setText("版本名称："+getVersionName());
        //2检查版本更新
        mLocalVersionCode = getVersionCode();
        //3获取服务器版本(json,xml)
        checkVersion();
    }

    //检测版本
    void checkVersion() {
        new Thread(){

            public void run(){
                Message msg=Message.obtain();
                long startime = System.currentTimeMillis();
                try{
                    if (true){
                        msg.what=ENTER_HOME;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //休眠
                    long endTime= System.currentTimeMillis();
                    if(endTime-startime<4000){
                        try{
                            Thread.sleep(4000-(endTime-startime));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }


    private String getVersionName() {
        //1包管理者对象PackageManager
        PackageManager pm=getPackageManager();
        //2从包的管理者中获得版本信息,传0获取基本信息；
        try {
            PackageInfo packageInfo=pm.getPackageInfo(getPackageName(),0);
            //3获取版本名称
           return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initUI() {
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
    }

    public int getVersionCode() {
        PackageManager pm=getPackageManager();
        //2从包的管理者中获得版本信息,传0获取基本信息；
        try {
            PackageInfo packageInfo=pm.getPackageInfo(getPackageName(),0);
            //3获取版本名称
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
