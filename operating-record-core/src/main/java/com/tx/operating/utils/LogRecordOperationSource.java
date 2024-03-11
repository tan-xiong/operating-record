package com.tx.operating.utils;

import com.alibaba.fastjson.JSONObject;
import com.tx.operating.annotation.LogRecordAnnotation;
import com.tx.operating.expression.json.JsonExpressionParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author tanxiong
 * @date 2023/1/28 15:33
 */
public class LogRecordOperationSource {


    /**
     * 获取方法上的注解数据
     *
     * @param point
     * @return
     */
    public static LogRecordAnnotation getAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        return Optional.ofNullable(signature.getMethod().getAnnotation(LogRecordAnnotation.class)).orElse(null);
    }

    /**
     * 获取SPEL表达式
     *
     * @param logRecord
     * @return
     */
    public static Map<String, Object> getBeforeExecuteFunctionTemplate(LogRecordAnnotation logRecord) {
        Class<?> aClass = LogRecordAnnotation.class;
        Method[] methods = aClass.getDeclaredMethods();
        Map<String, Object> spelMap = new HashMap<>();
        for (Method method : methods) {
            try {
                Object value = method.invoke(logRecord);
                spelMap.put(method.getName(), value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return spelMap;
    }

    public static Map<String, Object> processBeforeExecuteFunctionTemplate(Map<String, Object> spelMap, JSONObject param) {
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(param);
        JsonExpressionParser parser = new JsonExpressionParser();
        Map<String, Object> resultMap=new HashMap<>();
        for (Map.Entry<String, Object> temp : spelMap.entrySet()) {
            Expression expression = parser.parseExpression(String.valueOf(temp.getValue()), new TemplateParserContext());
            Object value = expression.getValue(standardEvaluationContext);
            resultMap.put(temp.getKey(), value);
        }

        return resultMap;
    }


}
