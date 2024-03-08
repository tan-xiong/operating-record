package com.tx.operating.handle;

import com.alibaba.fastjson.JSON;
import com.tx.operating.spi.KeepOperatingRecordSpi;

/**
 * @author tanxiong
 * @date 2024/3/7 9:52
 */
public class SqlSaveRecordSpi implements KeepOperatingRecordSpi {

    public SqlSaveRecordSpi(){

    }

    @Override
    public <T> void keepRecord(T t) {
        System.out.println("//////////////");
    }
}
