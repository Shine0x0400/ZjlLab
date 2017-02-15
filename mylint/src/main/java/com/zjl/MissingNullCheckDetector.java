package com.zjl;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.ArrayList;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.ConstructorInvocation;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.MethodInvocation;
import lombok.ast.Node;
import lombok.ast.VariableReference;

/**
 * Created by zjl on 2016/12/28.
 */

public class MissingNullCheckDetector extends Detector implements Detector.JavaScanner {
    public static final Issue ISSUE = Issue.create("MissingNullCheck",
            "Missing null-check before using nullable reference",
            "Should add null-check or @NonNull annotation before using nullable reference",
            Category.CORRECTNESS, 6, Severity.ERROR,
            new Implementation(MissingNullCheckDetector.class, Scope.JAVA_FILE_SCOPE));

    @Override
    public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
        return new MissingNullCheckVisitor(context);
    }

    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {
        List<Class<? extends lombok.ast.Node>> types =
                new ArrayList<>();
        types.add(VariableReference.class);
        types.add(MethodInvocation.class);

        return types;
    }


    private final class MissingNullCheckVisitor extends ForwardingAstVisitor {
        private JavaContext mContext;

        private MissingNullCheckVisitor(JavaContext context) {
            this.mContext = context;
        }

        @Override
        public boolean visitMethodInvocation(MethodInvocation node) {
            if ("thisMethodIntendForDebuggingOnly".equals(node.astName().astValue())) {
                Node operand = node.rawOperand();

                if (!isOperandReliable(operand)) {
                    mContext.report(ISSUE,
                            node,
                            mContext.getLocation(node),
                            "You should check the operand's nullness before invoking method on it.");
                }
            }
            return true;
        }

        @Override
        public boolean visitVariableReference(VariableReference node) {
            return super.visitVariableReference(node);
        }

        /**
         * check whether the operand passed in is reliable that means it isn't null in anyway.
         *
         * @param operand to check.
         * @return true when the operand is not null in anyway, false otherwise.
         */
        private boolean isOperandReliable(Node operand) {
            /* new instance */
            if (operand instanceof ConstructorInvocation) {
                return true;
            }

            /* in if(xxx!=null) block */

            /* annotated by @NonNull */


            return false;
        }
    }

}
