package com.zjl.map;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zjl on 2017/5/10.
 */

public class MapMarkerInfoView extends FrameLayout {
    private LinearLayout mNavLayout;
    private TextView mInfoTv;
    private OnNavClickListener mOnNavClickListener;

    public MapMarkerInfoView(Context context) {
        super(context);
        inflate(getContext(), R.layout.map_marker_info_view, this);

        mInfoTv = (TextView) findViewById(R.id.info_tv);
        mNavLayout = (LinearLayout) findViewById(R.id.nav_layout);
        mNavLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnNavClickListener != null) {
                    mOnNavClickListener.onNavClick(MapMarkerInfoView.this);
                }
            }
        });
    }

    public void setInfo(String info) {
        mInfoTv.setText(info);
    }

    public void setOnNavClickListener(OnNavClickListener listener) {
        mOnNavClickListener = listener;
    }


    interface OnNavClickListener {
        void onNavClick(MapMarkerInfoView view);
    }
}
