package com.zjl.deviceinfo;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by zjl on 2017/3/9.
 */

public class DeviceInfo {
    private static DeviceInfo mInstance;
    private int screenWidth;
    private int screenHeight;

    private float density;
    private int densityDpi;

    public static DeviceInfo getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DeviceInfo.class) {
                if (mInstance == null) {
                    mInstance = new DeviceInfo(context.getApplicationContext());
                }
            }
        }

        return mInstance;
    }

    private DeviceInfo(Context application) {
        WindowManager manager = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);

        Point point = new Point();
        manager.getDefaultDisplay().getRealSize(point);
        screenWidth = point.x;
        screenHeight = point.y;

        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();

        density = displayMetrics.density;
        densityDpi = displayMetrics.densityDpi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(screenWidth).append(" X ").append(screenHeight).append("\n")
                .append("density:").append("\t").append(density).append("\n")
                .append("densityDpi:").append("\t").append(densityDpi).append("\n");
        return sb.toString();
    }
}
