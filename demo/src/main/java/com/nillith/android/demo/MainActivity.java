package com.nillith.android.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.versionCode)).setText(BuildConfig.VERSION_CODE + "");
        ((TextView) findViewById(R.id.versionName)).setText(BuildConfig.VERSION_NAME);
    }
}
