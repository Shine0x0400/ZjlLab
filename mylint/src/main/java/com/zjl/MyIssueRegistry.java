package com.zjl;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zjl on 2016/12/28.
 */

public class MyIssueRegistry extends IssueRegistry {
    @Override
    public List<Issue> getIssues() {
        System.out.println("!!!!!!!!!!!!!MyIssueRegistry lint rules works!!!!!!!!!!!!!");
        return Arrays.asList(
                MissingNullCheckDetector.ISSUE
        );
    }
}
