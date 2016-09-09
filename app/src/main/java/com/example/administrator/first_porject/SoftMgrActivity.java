package com.example.administrator.first_porject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.first_porject.ui.BaseActivity;

public class SoftMgrActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_mgr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        ProgressBar phone = (ProgressBar) findViewById(R.id.progressBar1);
        ProgressBar sdcard = (ProgressBar) findViewById(R.id.progressBar2);
        TextView phoneinfo = (TextView) findViewById(R.id.tv_phone_space_msg);
        TextView sdcardinfo = (TextView) findViewById(R.id.tv_phone_sdcard_msg);
        long phoneMax = MemoryManager.getPhoneSelfSDCardSize();
        phone.setMax((int)(phoneMax/1024/1024));
        long phoneMin = MemoryManager.getPhoneSelfSDCardFreeSize();
        phone.setProgress((int)((phoneMax-phoneMin)/1024/1024));
        String allSize = Formatter.formatFileSize(this, phoneMax);
        String useSize = Formatter.formatFileSize(this, phoneMax-phoneMin);
        phoneinfo.setText("内存使用情况："+useSize+"/"+allSize);

        long sdcardMax = MemoryManager.getPhoneOutSDCardSize(this);
        sdcard.setMax((int)(phoneMax/1024/1024));
        long sdcardMin = MemoryManager.getPhoneOutSDCardFreeSize(this);
        sdcard.setProgress((int) ((sdcardMax-sdcardMin) / 1024 / 1024));
        String allSDSize = Formatter.formatFileSize(this, sdcardMax);
        String useSDSize = Formatter.formatFileSize(this, sdcardMax-sdcardMin);
        sdcardinfo.setText("内存使用情况："+useSDSize+"/"+allSDSize);

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void initView() {

    }

    public void showSoftInfo(View view){
        switch (view.getId()){
            case R.id.tv_softinfo_all :
                Intent intent0 = new Intent(this,ShowUserSoftActivity.class);
                intent0.putExtra("flag",0);
                startActivity(intent0);
                break;
            case R.id.tv_softinfo_system :
                Intent intent1 = new Intent(this,ShowUserSoftActivity.class);
                intent1.putExtra("flag",1);
                startActivity(intent1);
                break;
            case R.id.tv_softinfo_user :
                Intent intent2 = new Intent(this,ShowUserSoftActivity.class);
                intent2.putExtra("flag",2);
                startActivity(intent2);
                break;
        }
    }
}
