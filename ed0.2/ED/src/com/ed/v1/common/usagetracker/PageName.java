package com.ed.v1.common.usagetracker;

import java.lang.annotation.*;

/**
 * Created by haijun.wang on 15/3/10.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PageName {
    public String value();
}
