package com.example.administrator.first_porject;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.example.administrator.first_porject.adapter.ShowSoftAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShowUserSoftActivity extends AppCompatActivity implements View.OnClickListener {
    public final int MESSAGE_OK = 1;
    private ShowSoftAdapter adapter;
    ArrayList<String> delPackageNames;
    private ProgressDialog mDialog;
    MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_soft);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*LinearLayout softmgrLayout = (LinearLayout) findViewById(R.id.layout_softmgr_showapp);
        softmgrLayout.setVisibility(View.INVISIBLE);*/

        mDialog = new ProgressDialog(this);
        /*mDialog.setTitle("数据加载中");*/
        mDialog.setMessage("数据加载中");
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.show();


        ListView listView = (ListView) findViewById(R.id.user_app_lv);
        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag", -1);

        asyncLoadData(flag);

        adapter = new ShowSoftAdapter(this, selectPackages(flag));
        listView.setAdapter(adapter);
        CheckBox checkBoxAll = (CheckBox) findViewById(R.id.softmgr_checkall);
        assert checkBoxAll != null;
        checkBoxAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.setAllItemChecked(isChecked);
                adapter.notifyDataSetInvalidated();
            }
        });
        Button bt_all = (Button) findViewById(R.id.softmgr_deleteall);
        bt_all.setOnClickListener(this);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MESSAGE_OK){
                ArrayList<PackageInfo> data = (ArrayList<PackageInfo>) msg.obj;
                mDialog.dismiss();
                adapter.setDataToAdapter(data);
                adapter.notifyDataSetChanged();
            }
        }
    };

    private void asyncLoadData(final int flag) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<PackageInfo> data = selectPackages(flag);
                Message m = new Message();
                m.what = MESSAGE_OK;
                m.obj = data;
                mHandler.sendMessage(m);
            }
        }.start();

    }

    private ArrayList<PackageInfo> selectPackages(int flag){
        PackageManager pm = getPackageManager();
        ArrayList<PackageInfo> allData = (ArrayList<PackageInfo>) pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        ArrayList<PackageInfo> data = new ArrayList<>();
        if(flag == 0){
            return allData;
        }
        Iterator<PackageInfo> iterator = allData.iterator();
        while (iterator.hasNext()){
            PackageInfo packageInfo = iterator.next();
            int flags = packageInfo.applicationInfo.flags;
            switch (flag){
                case 1:
                    if((flags & ApplicationInfo.FLAG_SYSTEM) == 1){
                        data.add(packageInfo);
                    }
                    break;
                case 2:
                    if((flags & ApplicationInfo.FLAG_SYSTEM) != 1){
                        data.add(packageInfo);
                    }
                    break;
            }
        }
        return data;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        delPackageNames = getUserInstalledPackageNames(adapter,adapter.getIsCheckeds());
        for (String packageName : delPackageNames) {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:"+packageName));
            startActivity(intent);
        }
    }

    private ArrayList<String> getUserInstalledPackageNames(ShowSoftAdapter adapter, HashMap<String, Boolean> isCheckeds) {
        ArrayList<String> packageNames = new ArrayList<>();
        Set<Map.Entry<String, Boolean>> entries = isCheckeds.entrySet();
        Iterator<Map.Entry<String, Boolean>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Boolean> next = iterator.next();
            String key = next.getKey();
            Boolean value = next.getValue();
            if(value){
                packageNames.add(key);
            }
        }
        return packageNames;
    }

    @Override
    protected void onResume() {
        super.onResume();
        myReceiver= new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme("package");
        registerReceiver(myReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            for (String name :delPackageNames ) {
                adapter.getIsCheckeds().remove(name);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
