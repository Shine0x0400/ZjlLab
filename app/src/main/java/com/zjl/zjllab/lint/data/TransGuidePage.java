package com.zjl.zjllab.lint.data;

import com.meituan.android.paycommon.lib.utils.JsonBean;

import java.io.Serializable;

/**
 * Created by qiu on 16/7/13.
 */
@JsonBean
public class TransGuidePage implements Serializable {
    //checked
    private String title;

    private String tip;

    private ModelA[] agreements;

    private String submitUrl;

    private String cancelButton;

    private String submitButton;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public ModelA[] getAgreements() {
        return agreements;
    }

    public void setAgreements(ModelA[] agreements) {
        this.agreements = agreements;
    }

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    public String getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(String cancelButton) {
        this.cancelButton = cancelButton;
    }

    public String getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(String submitButton) {
        this.submitButton = submitButton;
    }

}
