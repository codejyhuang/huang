package com.hrym.common.annotation;

import java.lang.annotation.*;

/**
 * 权限：无需登录
 * Created by qhzhang on 2017/2/11.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Allowed {
}
