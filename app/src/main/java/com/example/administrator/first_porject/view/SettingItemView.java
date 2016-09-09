package com.example.administrator.first_porject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.first_porject.R;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class SettingItemView extends RelativeLayout {
    private static final String NAMESPACE="http://schemas.android.com/apk/res/com.example.administrator.first_porject";
    private static final String tag="SettingItemView";

    private CheckBox cb_box;
    private TextView tv_des;
    private String destitle;
    private String desoff;
    private String deson;

    public SettingItemView(Context context) {
        this(context,null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //xml---view  将设置界面的一条目录转为view对象，直接添加到SettingItemView对应的view中
        View.inflate(context, R.layout.setting_item_view,this);
        TextView tv_title= (TextView) findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);
        cb_box = (CheckBox) findViewById(R.id.cb_box);


        initAttrs(attrs);
        tv_title.setText(destitle);
    }
    private void initAttrs(AttributeSet attrs){

        Log.i(tag,"attrs.getAttributeCount()="+attrs.getAttributeCount());
        for (int i=0;i<attrs.getAttributeCount();i++){
            Log.i(tag,"name="+attrs.getAttributeName(i));
            Log.i(tag,"value="+attrs.getAttributeValue(i));
            Log.i(tag,"===================");
        }
        destitle = attrs.getAttributeValue(NAMESPACE,"destitle");
        desoff = attrs.getAttributeValue(NAMESPACE,"desoff");
        deson = attrs.getAttributeValue(NAMESPACE,"deson");
        Log.i(tag, destitle);
        Log.i(tag, desoff);
        Log.i(tag, deson);
    }
    public boolean isCheck(){
        return cb_box.isChecked();
    }
    public void setCheck(boolean isCheck){
        cb_box.setChecked(isCheck);
        if (isCheck){
            tv_des.setText(deson);
        }else{
            tv_des.setText(desoff);
        }
    }

}
