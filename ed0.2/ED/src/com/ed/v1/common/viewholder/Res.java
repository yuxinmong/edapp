package com.ed.v1.common.viewholder;

import java.lang.annotation.*;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Res {
    public int value();
}
