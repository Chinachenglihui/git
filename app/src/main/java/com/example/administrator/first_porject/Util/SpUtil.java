package com.example.administrator.first_porject.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.AdapterView;

/**
 * Created by Administrator on 2016/8/11.
 */
public class SpUtil {
    private static SharedPreferences sp;
    //写
    public static void  putBoolean(Context ctx, String key, boolean value){
        //存储节点文件名称

        if (sp==null){
            sp=ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }
        //读
    public static boolean getBoolean(Context ctx, String key, boolean devalue){
        //存储节点文件名称

        if (sp==null){
            sp=ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, devalue);
    }
    public static void  putString(Context ctx, String key, String value){
        //存储节点文件名称

        if (sp==null){
            sp=ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }
    //读

    public static String getString(Context ctx, String key, String devalue){
        //存储节点文件名称

        if (sp==null){
            sp=ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getString(key, devalue);
    }
}

