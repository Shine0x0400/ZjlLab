package com.zjl.roundimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class TestRoundImageViewActivity extends AppCompatActivity {
    private EditText tl;
    private EditText tr;
    private EditText bl;
    private EditText br;

    private RoundImageView riv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tl = (EditText) findViewById(R.id.topLeft);
        tr = (EditText) findViewById(R.id.topRight);
        bl = (EditText) findViewById(R.id.bottomLeft);
        br = (EditText) findViewById(R.id.bottomRight);

        riv = (RoundImageView) findViewById(R.id.roundImageView);


        //test adding RoundImageView from code
        RoundImageView img = new RoundImageView(this);
        img.setImageResource(R.drawable.zhuyin);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setRadiusTopLeft(60f);
        img.setRadiusBottomRight(32.5f);
        ((ViewGroup) findViewById(R.id.img_container)).addView(img, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setCorners(View view) {
        float tlr = 0;
        if (!TextUtils.isEmpty(tl.getText().toString())) {
            tlr = Float.parseFloat(tl.getText().toString());
        }
        float trr = 0;
        if (!TextUtils.isEmpty(tr.getText().toString())) {
            trr = Float.parseFloat(tr.getText().toString());
        }
        float blr = 0;
        if (!TextUtils.isEmpty(bl.getText().toString())) {
            blr = Float.parseFloat(bl.getText().toString());
        }
        float brr = 0;
        if (!TextUtils.isEmpty(br.getText().toString())) {
            brr = Float.parseFloat(br.getText().toString());
        }

        riv.setCornerRadius(tlr, trr, brr, blr);
    }
}
