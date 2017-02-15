package com.zjl.zjllab.flag_activity_forward_result;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zjl.zjllab.R;

public class MediumActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 523;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
    }

    public void onJumpButtonClick(View view) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("forward_result");
        builder.authority("result");

        //NOTE: startActivityForResult will throw exception: Caused by: android.util.AndroidRuntimeException: FORWARD_RESULT_FLAG used while also requesting a result
//        startActivityForResult(new Intent(Intent.ACTION_VIEW, builder.build()).addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT), REQUEST_CODE);
        startActivity(new Intent(Intent.ACTION_VIEW, builder.build()).addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT));

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = data.getStringExtra(ResultActivity.RESULT);

        Log.i(getClass().getSimpleName(),
                "onActivityResult---requestCode=" + requestCode
                        + "resultCode=" + resultCode
                        + "result=" + result);
    }
}
