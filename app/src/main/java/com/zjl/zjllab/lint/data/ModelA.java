package com.zjl.zjllab.lint.data;

import com.meituan.android.paycommon.lib.utils.JsonBean;

import java.io.Serializable;

@JsonBean
public class ModelA implements Serializable {
    private String strField;
    private int intField;

    public ModelB refField;

    public String getStrField() {
        return strField;
    }

    public void setStrField(String strField) {
        this.strField = strField;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public ModelB getRefField() {
        return refField;
    }

    public void setRefField(ModelB refField) {
        this.refField = refField;
    }
}
