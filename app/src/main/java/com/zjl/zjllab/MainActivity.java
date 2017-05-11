package com.zjl.zjllab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.customizetoast.MyToast;
import com.zjl.roundimageview.TestRoundImageViewActivity;
import com.zjl.zjllab.PorterDuffXfermode.PorterDuffXfermodeActivity;
import com.zjl.zjllab.cases.CasesHelper;
import com.zjl.zjllab.flag_activity_forward_result.CallerActivity;

public class MainActivity extends AppCompatActivity implements CasesFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, CasesFragment.newInstance(1), "contentFragment")
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //通过setView(View)自定义的toast可以跨Activity context显示
        new MyToast().show(this);
    }

    @Override
    public void onListFragmentInteraction(CasesHelper.Case item) {
        if ("1".equals(item.id)) {
            startActivity(new Intent(this, PorterDuffXfermodeActivity.class));
        } else if ("2".equals(item.id)) {
            startActivity(new Intent(this, CallerActivity.class));
        } else if ("3".equals(item.id)) {
            startActivity(new Intent(this, TestRoundImageViewActivity.class));
        } else if ("4".equals(item.id)) {
            Uri uri = Uri.parse("iconch://www.meituan.com/map?latitude=39.906901&longitude=116.397972&title=xxx&name=班加罗尔");
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }
    }
}
