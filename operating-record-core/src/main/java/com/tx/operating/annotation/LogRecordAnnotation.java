package com.tx.operating.annotation;

import java.lang.annotation.*;

/**
 * @author tanxiong
 * @date 2023/1/28 14:11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecordAnnotation {

    //操作是否成功
    boolean succeed() default true;

    //操作人
    String operator() default "";

    //业务单号
    String bizNo() default "";

    //操作日志的种类
    String category() default "";

    //扩展参数，记录操作日志的修改详情
    String detail() default "";

    //记录日志的条件
    String condition() default "";
}
