package com.zjl.deviceinfo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;


public class DisplayService extends Service {
    public DisplayService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        TextView tv = new TextView(this);
        tv.setBackgroundColor(Color.TRANSPARENT);
        tv.setText(DeviceInfo.getInstance(this).toString());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = 500;
        lp.height = 700;
        lp.format = PixelFormat.RGBA_8888;
        lp.type = WindowManager.LayoutParams.TYPE_TOAST;
        lp.gravity = Gravity.TOP | Gravity.LEFT;
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        windowManager.addView(tv, lp);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
