package com.hrym.common.annotation;

import java.lang.annotation.*;

/**
 * Created by mj on 2017/7/2.
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value();
}
