package com.example.administrator.first_porject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public abstract class MyBaseAdapter<E> extends BaseAdapter {

    //把xml布局文件 加工成View 控件
    public LayoutInflater inflater;
    private ArrayList<E> data;

    public ArrayList<E> getData() {
        return data;
    }

    public void setData(ArrayList<E> data) {
        this.data = data;
    }

    public MyBaseAdapter(@NonNull ArrayList<E> data, @NonNull Context context) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return MyGetView(position,convertView,parent);
    }
    public abstract View MyGetView(int position, View convertView, ViewGroup parent);
}
