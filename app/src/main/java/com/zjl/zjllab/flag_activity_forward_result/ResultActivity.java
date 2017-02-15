package com.zjl.zjllab.flag_activity_forward_result;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zjl.zjllab.R;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT = "result";
    public static final int RESULT_CODE = 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public void onJumpButtonClick(View view) {
        Editable text = ((EditText) findViewById(R.id.result)).getText();
        Log.i(getClass().getSimpleName(), "onJumpButtonClick---result=" + text);
        setResult(RESULT_CODE, new Intent().putExtra(RESULT, text.toString()));
        finish();
    }
}
