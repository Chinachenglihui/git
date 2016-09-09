package com.example.administrator.first_porject.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.first_porject.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class BasePagerAdapter extends PagerAdapter {
    private ArrayList<View> views=new ArrayList<View>();
    private LayoutInflater inflater;
    private Context context;
    private int[]pics={R.mipmap.adware_style_applist, R.mipmap.adware_style_banner,R.mipmap.adware_style_creditswall};
    public BasePagerAdapter(Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
        for (int x=0;x<pics.length;x++){
       View v= inflater.inflate(R.layout.activity_lead,null);
            ImageView iv= (ImageView) v.findViewById(R.id.iv_lead1);
            iv.setImageResource(pics[x]);
            addViewToAdapter(v);
        }
    }
    public void addViewToAdapter(View v){
        views.add(v);
    }
    public ArrayList<View> getViews(){
        return views;
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v=views.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
