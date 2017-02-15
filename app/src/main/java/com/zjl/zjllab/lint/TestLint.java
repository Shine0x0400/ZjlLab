package com.zjl.zjllab.lint;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zjl on 2016/12/28.
 */

public class TestLint {
    public void testLogUtilsNotUsed() {
//        Log.d("tag", "not use LogUtils, should find out by lint");
    }

    public void testNullness(@NonNull String nonNull, @Nullable String nullable) {
        if (nonNull != null) {
            int l1 = nonNull.length();
        }

        int l2 = nullable.length();
    }
}
