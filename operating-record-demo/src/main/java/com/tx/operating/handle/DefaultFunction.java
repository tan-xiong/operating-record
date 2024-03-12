package com.tx.operating.handle;

import com.alibaba.fastjson2.JSON;
import com.tx.operating.service.IParseFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tanxiong
 * @date 2024/3/11 15:08
 */
@Slf4j
@Service
public class DefaultFunction implements IParseFunction {
    @Override
    public boolean executeBefore() {
        return true;
    }

    @Override
    public String functionName() {
        return DefaultFunction.class.getSimpleName();
    }

    @Override
    public String apply(Object value) {
        log.info("/// 入参"+ JSON.toJSONString(value));
        return "云南";
    }
}
