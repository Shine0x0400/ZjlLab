package com.zjl.zjllab.lint;

import android.support.annotation.NonNull;

import com.zjl.zjllab.lint.data.ModelA;

/**
 * Created by zjl on 2017/1/6.
 */

public class TestMissingNullCheck {
    public static void triger(TestMissingNullCheck check1, @NonNull TestMissingNullCheck check2) {

        /* new instance */
        new TestMissingNullCheck().thisMethodIntendForDebuggingOnly(null);

        /* in if(xxx!=null) block */
        if (check1 != null) {
            check1.thisMethodIntendForDebuggingOnly(null);
        }

        /* annotated by @NonNull */
        check2.thisMethodIntendForDebuggingOnly(null);
    }

    public void test1(ModelA modelA) {
        modelA.getRefField().getStrField().length();
    }

    public void thisMethodIntendForDebuggingOnly(ModelA modelA) {
        modelA.getRefField().getStrField().length();
    }
}
