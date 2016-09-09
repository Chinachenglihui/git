package com.example.administrator.first_porject.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.first_porject.ConstanValue;
import com.example.administrator.first_porject.MainActivity;
import com.example.administrator.first_porject.PhonemgrActivity;
import com.example.administrator.first_porject.R;
import com.example.administrator.first_porject.SettingActivity;
import com.example.administrator.first_porject.SetupOverActivity;
import com.example.administrator.first_porject.SoftMgrActivity;
import com.example.administrator.first_porject.Util.SpUtil;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class HomeActivity extends Activity {

    private GridView gv_home;
    private int[] mTitlestrs;
    private String[] titleStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        initDate();
    }

    private void initDate() {
        //准备数据
        titleStr = new String[]{
                "手机防盗", "通信卫视", "软件管理", "进程管家", "烈良统计", "手机拨号", "缓冲清理", "高级工具", "设置中心",
        };
        mTitlestrs = new int[]{
                R.mipmap.icon_filemgr,
                R.mipmap.icon_phonemgr,
                R.mipmap.icon_rocket,
                R.mipmap.icon_softmgr,
                R.mipmap.icon_telmgr,
                R.mipmap.icon_telmgr,
                R.mipmap.icon_sdclean,
                R.mipmap.icon_softmgr,
                R.mipmap.icon_telmgr
        };
        gv_home.setAdapter(new MyAdapter());
        //注册九宫格单个点击事件
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent22 = new Intent(getApplicationContext(), PhonemgrActivity.class);
                        startActivity(intent22);
                        break;
                    case 8:
                        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(getApplicationContext(), SetupOverActivity.class);
                        startActivity(intent1);
                        break;
                    case 3:
                        Intent intent11 = new Intent(getApplicationContext(), SoftMgrActivity.class);
                        startActivity(intent11);
                        break;
                    case 4:
                        break;
                    case 5:
                        Intent intent2 = new Intent(getApplicationContext(), ShowTelTypeActivity.class);
                        startActivity(intent2);
                        break;
                    case 6:
                        break;
                    case 7:
                        Intent intent3 = new Intent(getApplicationContext(), com.example.administrator.first_porject.view.MainActivity.class);
                        startActivity(intent3);
                        break;
                    case 1:
                        break;

                }
            }

        });
    }
           /* protected void showDialog() {
                String psd=SpUtil.getString(this, ConstanValue.MOBILE_SAFE_PSD,"");
                if (TextUtils.isEmpty(psd)){
                    showSetPsdDialog();
                }else{
                    showConfirmPsdDialog();
                }
            }

    private void showConfirmPsdDialog() {

    }

    private void showSetPsdDialog() {

    }
*/

    private void initUI() {
        gv_home = (GridView) findViewById(R.id.gv_home);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mTitlestrs.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitlestrs[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           View view= View.inflate(getApplicationContext(),R.layout.gridview_item,null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            TextView tv_title= (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(titleStr[position]);
            iv_icon.setBackgroundResource(mTitlestrs[position]);
            return view;
        }
    }
}
