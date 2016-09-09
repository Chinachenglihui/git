package com.example.administrator.first_porject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public abstract class MyAdapter <E> extends BaseAdapter {
    protected ArrayList<E> datas = new ArrayList<E>();
    LayoutInflater layoutInflater;
    protected Context context;

    public List<E> getDataFromAdapter() {
        return datas;
    }
    public void setDataToAdapter(ArrayList<E> e) {
        datas = e;
    }
    // 添加数据到当前适配器集合
    public void addDataToAdapter( E e) {
        if (e != null) {
            datas.add(e);
        }
    }
    public MyAdapter(Context context, ArrayList <E>list) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        datas = list;
    }
    public MyAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public E getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getMyView(position, convertView, parent);
    }

    abstract public View getMyView(int position, View convertView, ViewGroup parent);
}
