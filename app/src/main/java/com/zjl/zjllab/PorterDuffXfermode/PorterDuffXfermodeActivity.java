package com.zjl.zjllab.PorterDuffXfermode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zjl.zjllab.R;

public class PorterDuffXfermodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawableResource(R.color.transparent_totally);

        setContentView(new CanvasView(this));
    }
}
