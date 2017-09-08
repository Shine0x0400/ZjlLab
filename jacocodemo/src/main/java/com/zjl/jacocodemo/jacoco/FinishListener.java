package com.zjl.jacocodemo.jacoco;

/**
 * Created by zjl on 2017/9/7.
 */

public interface FinishListener {
    void onActivityFinished();

    void dumpIntermediateCoverage(String filePath);
}
