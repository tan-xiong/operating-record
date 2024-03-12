package com.tx.operating.spi.impl;

import com.alibaba.fastjson.JSON;
import com.tx.operating.spi.KeepOperatingRecordSpi;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanxiong
 * @date 2024/3/7 9:52
 */
@Slf4j
public class DefaultSqlSaveRecordSpi implements KeepOperatingRecordSpi {

    public DefaultSqlSaveRecordSpi(){

    }

    @Override
    public <T> void keepRecord(T t) {
        log.info("/// log={}",JSON.toJSONString(t));
    }
}
