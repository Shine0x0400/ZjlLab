package com.zjl.deviceinfo;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DeviceInfo";
    private static final String LINE = "\n";

    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTextView = (TextView) findViewById(R.id.info_text);


        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        String size = "the screen size is " + point.toString();
        Log.d(TAG, size);
        getWindowManager().getDefaultDisplay().getRealSize(point);
        String realSize = "the screen real size is " + point.toString();
        Log.d(TAG, realSize);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        String density = "Density is " + displayMetrics.density + " densityDpi is " + displayMetrics.densityDpi + " height: " + displayMetrics.heightPixels +
                " width: " + displayMetrics.widthPixels;
        Log.d(TAG, density);

//        StringBuilder sb = new StringBuilder();
//        sb.append(size).append(LINE)
//                .append(realSize).append(LINE)
//                .append(density);
        infoTextView.setText(DeviceInfo.getInstance(this).toString());

        startService(new Intent(this, DisplayService.class));
    }
}
