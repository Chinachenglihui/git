package com.example.administrator.first_porject.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.first_porject.R;
import com.example.administrator.first_porject.entity.TelClassList;

import java.util.ArrayList;

public class TelClassAdapter extends MyBaseAdapter<TelClassList>{

    public TelClassAdapter(@NonNull ArrayList<TelClassList> data, Context context) {
        super(data,context);
    }

    @Override
    public View MyGetView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_main_tel_type, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(getData().get(position).getName());
        return view;
    }


}
