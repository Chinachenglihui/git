package com.example.administrator.first_porject;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class FocusTextView extends TextView {


    public FocusTextView(Context context) {
        super(context);
    }

    public FocusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
