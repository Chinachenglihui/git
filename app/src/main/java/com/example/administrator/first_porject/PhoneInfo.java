package com.example.administrator.first_porject;


/**
 * Created by Administrator on 2016/9/7 0007.
 */
import android.graphics.drawable.Drawable;

public class PhoneInfo {
    private Drawable icon;
    private String text;
    private String title;

    public PhoneInfo() {
    }

    public PhoneInfo(String title, String text, Drawable icon) {
        title = title;
        text = text;
        icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        text = text;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        icon = icon;
    }
}
