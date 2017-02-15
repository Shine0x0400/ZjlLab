package com.example.customizetoast;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zjl on 2016/12/27.
 */

public class MyToast {
    private Toast mToast;

    public void show(Context context) {
//        Toast.makeText(context, "system toast", Toast.LENGTH_LONG).show();

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundResource(R.color.toast_bg);

        TextView textView = new TextView(context);
        textView.setText("customize toast");
        textView.setTextColor(Color.RED);

        ImageView image = new ImageView(context);
        image.setImageResource(R.drawable.icon);

        linearLayout.addView(textView);
        linearLayout.addView(image);

        Toast toast = new Toast(context);
        toast.setView(linearLayout);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);

        toast.show();
    }
}
