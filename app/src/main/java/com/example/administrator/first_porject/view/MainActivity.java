package com.example.administrator.first_porject.view;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.first_porject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);
        Button bt_start = (Button) findViewById(R.id.bt_start);
        Button bt_stop = (Button) findViewById(R.id.bt_stop);
        assert bt_start != null;
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(),RocketService.class));
                finish();
            }
        });
        assert bt_stop != null;
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(),RocketService.class));
                finish();
            }
        });
    }
}

