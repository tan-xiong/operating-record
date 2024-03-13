package com.tx.operating.factorys;

import com.tx.operating.service.IParseFunction;
import com.tx.operating.utils.FindImplementationsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanxiong
 */

public class ParseFunctionFactory {

    private Map<String, IParseFunction> allFunctionMap;

    public ParseFunctionFactory() {
        // 通过反射获取所有IParseFunction接口的实现类
        List<Object> parseFunctions = FindImplementationsUtil.findImplementations(IParseFunction.class);
        if (CollectionUtils.isEmpty(parseFunctions)) {
            return;
        }
        allFunctionMap = new HashMap<>();
        for (Object obj : parseFunctions) {
            if (!(obj instanceof IParseFunction)) {
                continue;
            }
            IParseFunction parseFunction = (IParseFunction) obj;
            if (StringUtils.isEmpty(parseFunction.functionName())) {
                continue;
            }
            allFunctionMap.put(parseFunction.functionName(), parseFunction);
        }
    }

    public IParseFunction getFunction(String functionName) {
        return allFunctionMap.get(functionName);
    }
}