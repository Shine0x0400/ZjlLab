package com.meituan.android.paycommon.lib.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liuqi on 14-12-5.
 * 用于混淆时排除需要Json自动反序列化的Bean
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface JsonBean {
}
