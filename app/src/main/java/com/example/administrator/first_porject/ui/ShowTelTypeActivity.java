package com.example.administrator.first_porject.ui;



import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.first_porject.R;
import com.example.administrator.first_porject.Util.MyAssetManager;
import com.example.administrator.first_porject.adapter.TelClassAdapter;
import com.example.administrator.first_porject.db.DBRead;
import com.example.administrator.first_porject.entity.TelClassList;

import java.io.File;
import java.util.ArrayList;


public class ShowTelTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private TelClassAdapter adapter;
    private ArrayList<TelClassList> data;
    private File file;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tel_type);
        initToolBar();
        initView();
    }

    @Override
    public void initView() {

        listView = (ListView) findViewById(R.id.show_tel_class_list_view);
        //data
        file = MyAssetManager.copyAssetsFileToSDFile(this);
        data = DBRead.readTelClassList(file);
        //create adapter
        adapter = new TelClassAdapter(data, this);
        //设置适配器
        listView.setAdapter(adapter);
        //设置listview条目点击事件
        listView.setOnItemClickListener(this);

//        new QueryTask().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳到指定界面
        TelClassList tel = (TelClassList) parent.getItemAtPosition(position);
        //bundle是一个好比一个数据包
        Bundle bundle = new Bundle();
        bundle.putSerializable("file", file);
        bundle.putInt("idx", tel.getIdx());
        myStartActivity(ShowTelNumActivity.class,bundle);
    }

    class QueryTask extends AsyncTask<Void, Void, ArrayList<TelClassList>> {
        @Override
        protected ArrayList<TelClassList> doInBackground(Void... params) {
            file = MyAssetManager.copyAssetsFileToSDFile(ShowTelTypeActivity.this);
            return DBRead.readTelClassList(file);
        }

        @Override
        protected void onPostExecute(ArrayList<TelClassList> telClassLists) {
            //super.onPostExecute(telClassLists);
            if (telClassLists != null)
                showTestToast("onpostex"+telClassLists.size());
            data = telClassLists;
//            adapter.notifyDataSetChanged();
        }
    }

}
