package com.example.administrator.first_porject.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.first_porject.R;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class ActionBarView extends LinearLayout {
    private ImageView left;
    private ImageView right;
    private TextView tv;
    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.acticity_actionbar,this);
        left = (ImageView) findViewById(R.id.iv_left);
        right = (ImageView) findViewById(R.id.iv_right);
        tv = (TextView) findViewById(R.id.tv_title);
    }
    public void initActionBar(String title, int leftResID, int rightResID,
                              OnClickListener listener) {
        tv.setText(title);
        if (leftResID == -1) {
            left.setVisibility(View.INVISIBLE);
        } else {
            left.setImageResource(leftResID);
            left.setOnClickListener(listener);
        }
        if (rightResID == -1) {
            right.setVisibility(View.INVISIBLE);
        } else {
            right.setImageResource(rightResID);
            right.setOnClickListener(listener);
        }
    }
}
