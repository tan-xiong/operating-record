package com.tx.operating.service;

/**
 * @author tanxiong
 */
public interface IParseFunction {

    /**
     * 自定义函数名 用于@LogRecordAnnotation中进行模板配置
     */
    String functionName();

    /**
     * 执行逻辑
     */
    String apply(Object value);
}