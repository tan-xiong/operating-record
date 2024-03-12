package com.tx.operating.service;

/**
 * @author tanxiong
 */
public interface IParseFunction {

    default boolean executeBefore() {
        return false;
    }

    String functionName();

    String apply(Object value);
}