package com.example.administrator.first_porject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.first_porject.adapter.PhonemgrAdapter;
import com.example.administrator.first_porject.ui.BaseActivity;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class PhonemgrActivity extends BaseActivity implements View.OnClickListener {


    private PhonemgrActivity.BatteryBroadcastReceiver broadcastReceiver;
    private View.OnClickListener clickListener;
    private int currentBattery;
    private ListView exListView;
    private View layout_battery;
    private ProgressBar pb_battery;
    private ProgressBar pb_loading;
    private PhonemgrAdapter phonemgrAdapter;
    private int temperatureBattery;
    private TextView tv_battery;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonemgr);
        initMainButton();
        exListView = (ListView) findViewById(R.id.listviewLoad);
        phonemgrAdapter = new PhonemgrAdapter(this);
        exListView.setAdapter(phonemgrAdapter);
        // 初始化手机检测信息
        new Thread(new Runnable() {
            @Override
            public void run() {
                initAdapterData();
            }
        }).start();

    }

    @Override
    public void initView() {

    }

    private void initMainButton() {
        layout_battery = findViewById(R.id.ll_layout_battery);
        layout_battery.setOnClickListener(this);
        tv_battery = (TextView) findViewById(R.id.tv_battery);
        pb_battery = (ProgressBar) findViewById(R.id.pb_battery);
        pb_loading = (ProgressBar) findViewById(R.id.progressBar);
    }
    public class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                Bundle bundle = intent.getExtras();
                int maxBattery = (Integer)
                        bundle.get(BatteryManager.EXTRA_SCALE); // 总电量
                currentBattery = (Integer)
                        bundle.get(BatteryManager.EXTRA_LEVEL); // 当前电量
                temperatureBattery = (Integer)
                        bundle.get(BatteryManager.EXTRA_TEMPERATURE); // 电池温度
                pb_battery.setMax(maxBattery);
                pb_battery.setProgress(currentBattery);
                int current100 = currentBattery * 100 / maxBattery;
                tv_battery.setText(current100 + "%");//TextView 显示电量
            }
        }
    }
    public void initAdapterData() {
        PhoneManager manager = PhoneManager.getPhoneManage(this);
        String title;
        String text;
        Drawable icon;
        title = "设备名称:" + manager.getPhoneName1();
        text = "系统版本:" + manager.getPhoneSystemVersion();

        title = "全部运行内存" + CommonUtil.getFileSize(MemoryManager.getPhoneTotalRamMemory());
        text = "剩余运行内存" + CommonUtil.getFileSize(MemoryManager.getPhoneFreeRamMemory(this));

        title = "cpu名称:" + manager.getPhoneCpuName();
        text = "cpu数量:" + manager.getPhoneCpuNumber();

  //      title = "手机分辩率:" + manager.getResolution();
//        text = "相机分辩率:" + manager.getMaxPhotoSize();

        title = "基带版本:" + manager.getPhoneSystemBasebandVersion();
        text = "是否 ROOT:" + (manager.isRoot() ? "是" : "否");
    }
    protected void onDestroy() {
        super.onDestroy();

        try {
                unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Receiver not registered")) {
                        // Ignore this exception. This is exactly what is desired
                    } else {
                       // unexpected, re-throw
                       throw e;
                   }
            }


    }
    @Override
    public void onClick(View v) {

    }
}
