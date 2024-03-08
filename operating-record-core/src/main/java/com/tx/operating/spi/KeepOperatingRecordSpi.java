package com.tx.operating.spi;

/**
 * @author tanxiong
 * @date 2024/3/7 10:45
 */
public interface KeepOperatingRecordSpi {

   <T> void keepRecord(T t);
}
