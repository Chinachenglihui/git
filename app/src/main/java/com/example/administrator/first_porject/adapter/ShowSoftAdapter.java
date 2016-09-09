package com.example.administrator.first_porject.adapter;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.first_porject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class ShowSoftAdapter extends MyAdapter<PackageInfo> {

    private final PackageManager pm;

    public HashMap<String, Boolean> getIsCheckeds() {
        return isCheckeds;
    }

    private HashMap<String,Boolean> isCheckeds = new HashMap<>();
    public ShowSoftAdapter(Context context, ArrayList<PackageInfo> list) {
        super(context, list);
        pm = context.getPackageManager();
    }

    @Override
    public View getMyView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_showsoftinfo,null);
        PackageInfo packageInfo = getDataFromAdapter().get(position);
        int versionCode = packageInfo.versionCode;
        final String packageName = packageInfo.packageName;
        CharSequence lable = packageInfo.applicationInfo.loadLabel(pm);
        Drawable drawable = packageInfo.applicationInfo.loadIcon(pm);
        // convertView.findViewById(R.id.softmgr_lv).setBackgroundDrawable(drawable);
        ImageView iv_app = (ImageView) convertView.findViewById(R.id.item_speed_iv);
        iv_app.setBackgroundDrawable(drawable);
        TextView tv_appName = (TextView) convertView.findViewById(R.id.item_speed_appname);
        tv_appName.setText(lable);
        TextView tv_app_package_name = (TextView) convertView.findViewById(R.id.item_speed_memory);
        tv_app_package_name.setText(packageName);
        TextView tv_app_code = (TextView) convertView.findViewById(R.id.item_speed_isapp);
        tv_app_code.setText(versionCode+"");
        final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.item_softmgr_checkBox);
        Boolean aBoolean = isCheckeds.get(position);
        checkBox.setChecked(aBoolean==null?false:aBoolean);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBox.setChecked(isChecked);
                    isCheckeds.put(packageName,isChecked);
                }
            }
        });
        return convertView;
    }

    public void setAllItemChecked(Boolean ischecked){
        List<PackageInfo> datas = getDataFromAdapter();
        List<String>  packageNames = new ArrayList<>();
        for(PackageInfo packageInfo : datas){
            packageNames.add(packageInfo.packageName);
        }
        for(int i = 0;i < getDataFromAdapter().size();i++){
            isCheckeds.put(packageNames.get(i),ischecked);
        }
    }
}
