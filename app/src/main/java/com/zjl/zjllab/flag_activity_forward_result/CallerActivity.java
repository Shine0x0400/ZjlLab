package com.zjl.zjllab.flag_activity_forward_result;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zjl.zjllab.R;

public class CallerActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 522;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller);
    }

    public void onJumpButtonClick(View view) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("forward_result");
        builder.authority("medium");

        startActivityForResult(new Intent(Intent.ACTION_VIEW, builder.build()), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = null;
        if (data != null) {
            result = data.getStringExtra(ResultActivity.RESULT);
        }

        Log.i(getClass().getSimpleName(),
                "onActivityResult---requestCode=" + requestCode
                        + "resultCode=" + resultCode
                        + "result=" + result);

        if (requestCode == REQUEST_CODE && resultCode == ResultActivity.RESULT_CODE) {
            ((EditText) findViewById(R.id.result)).setText(result);
        }
    }
}
