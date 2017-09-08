package com.zjl.jacocodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.exe_btn);
        mEdit = (EditText) findViewById(R.id.input_edit);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int num = 0;
        try {
            num = Integer.valueOf(mEdit.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        switch (num) {
            case 1:
                Log.d("JacocoDemo", "execute--" + num);
                break;
            case 2:
                Log.d("JacocoDemo", "execute--" + num);
                break;
            case 3:
                Log.d("JacocoDemo", "execute--" + num);
                break;
            case 4:
                Log.d("JacocoDemo", "execute--" + num);
                break;
            case 5:
                Log.d("JacocoDemo", "execute--" + num);
                break;
            default:
                Log.d("JacocoDemo", "execute--default");
                break;
        }

    }
}
