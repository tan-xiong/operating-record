package com.tx.operating.spi.handle;

import com.alibaba.fastjson.JSON;
import com.tx.operating.spi.KeepOperatingRecordSpi;

/**
 * @author tanxiong
 * @date 2024/3/7 9:52
 */
public class DefaultSqlSaveRecordSpi implements KeepOperatingRecordSpi {

    public DefaultSqlSaveRecordSpi(){

    }

    @Override
    public <T> void keepRecord(T t) {
        System.out.println(JSON.toJSONString(t));
    }
}
