package com.example.administrator.first_porject.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.first_porject.R;
import com.example.administrator.first_porject.SplashActivity;
import com.example.administrator.first_porject.Util.LogUtil;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class LeadActivity extends BaseActivity {
    private ViewPager mViewPager;
    private BasePagerAdapter mBasePagerAdapter;
    private ImageView[] poins = new ImageView[3];
    private TextView mTextView;

    @Override
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_lead);
        mBasePagerAdapter = new BasePagerAdapter(this);
        poins[0] = (ImageView) findViewById(R.id.iv_lead1);
        poins[1] = (ImageView) findViewById(R.id.iv_lead2);
        poins[2] = (ImageView) findViewById(R.id.iv_lead3);
        mTextView= (TextView) findViewById(R.id.tv_lead2);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);

        initView();
//        SharedPreferences shared=getSharedPreferences("first.adn", Context.MODE_PRIVATE);
//        boolean isFirst =shared.getBoolean("first",true);
//        if (!isFirst){
//            startActivity(this,SplashActivity.class);
//        }
//        else {
//            SharedPreferences.Editor edit=shared.edit();
//            edit.putBoolean("first",false);
//        }
       setListenrt();

    }




    private void setListenrt() {

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtil.logD("onPageScrolled", position + "," + positionOffset + "," + positionOffsetPixels);



            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.logD("onPageSelected", position + "");
                mTextView.setVisibility(View.INVISIBLE);
                if (position==poins.length-1){
                    mTextView.setVisibility(View.VISIBLE);
                    mTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LeadActivity.this,SplashActivity.class);
                            startActivity(intent);
                        }


                    });
                }

//                for (int i=0;i<3;i++){
//                    poins[i].setImageResource(R.mipmap.adware_style_default);
//                }
//                poins[position].setImageResource(R.mipmap.adware_style_selected);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtil.logD("onPageScrollStateChanged", state + "");

            }
        });
        mViewPager.setAdapter(mBasePagerAdapter);
    }


}
