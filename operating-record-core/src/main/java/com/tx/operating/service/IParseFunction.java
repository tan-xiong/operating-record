package com.tx.operating.service;

/**
 * @author tanxiong
 */
public interface IParseFunction {

    String functionName();

    String apply(Object value);
}