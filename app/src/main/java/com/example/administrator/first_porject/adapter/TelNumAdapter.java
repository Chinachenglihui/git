package com.example.administrator.first_porject.adapter;



import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.first_porject.R;
import com.example.administrator.first_porject.entity.TelNumInfo;

import java.util.ArrayList;


public class TelNumAdapter extends MyBaseAdapter<TelNumInfo> {


    public TelNumAdapter(@NonNull ArrayList<TelNumInfo> data, @NonNull Context context) {
        super(data, context);
    }

    @Override
    public View MyGetView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_main_tel_num, null);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_num = (TextView) view.findViewById(R.id.tv_num);

        tv_name.setText(getData().get(position).getName());
        tv_num.setText(getData().get(position).getTelNum()+"");
        return view;
    }
}

