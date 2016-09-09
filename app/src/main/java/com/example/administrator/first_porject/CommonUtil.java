package com.example.administrator.first_porject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class CommonUtil {
    /**
     * 将毫秒转为字符串方式的时间格式 (yyyy-MM-dd hh:mm:ss)
     *
     * @param filetime
     * @return
     */
    public static String getStrTime(long filetime) {
        if (filetime == 0) {
            return "未知";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String ftime = formatter.format(new Date(filetime));
        return ftime;
    }

    public static String getStrTime() {
        SimpleDateFormat formatter = new
                SimpleDateFormat("yyyyMMddhhmmss");
        String ftime = formatter.format(new
                Date(System.currentTimeMillis()));
        return ftime;
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a == null ? false : a.equals(b));
    }
    /**
     * 得到字符串方式的文件大小
     *
     * @param filesize
     *            ,单位 b
     * @return
     */
    private static DecimalFormat df = new DecimalFormat("#.00");
    /**单位换算*/
    public static String getFileSize(long filesize) {
        StringBuffer mstrbuf = new StringBuffer();
        if (filesize < 1024) {
            mstrbuf.append(filesize);
            mstrbuf.append(" B");
        }
        else if (filesize < 1048576) {
            mstrbuf.append(df.format((double) filesize / 1024));
            mstrbuf.append(" K");
        } else if (filesize < 1073741824) {
            mstrbuf.append(df.format((double) filesize / 1048576));
            mstrbuf.append(" M");
        } else {
            mstrbuf.append(df.format((double) filesize / 1073741824));
            mstrbuf.append(" G");
        }
        return mstrbuf.toString();
    }
}