package com.zjl.jacocodemo.jacoco;

import android.util.Log;

import com.zjl.jacocodemo.MainActivity;

/**
 * Created by zjl on 2017/9/7.
 */

public class InstrumentedActivity extends MainActivity {
    public static String TAG = "InstrumentedActivity";

    private FinishListener mListener;

    public void setFinishListener(FinishListener listener) {
        mListener = listener;
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.finish();
        if (mListener != null) {
            mListener.onActivityFinished();
        }
    }
}
