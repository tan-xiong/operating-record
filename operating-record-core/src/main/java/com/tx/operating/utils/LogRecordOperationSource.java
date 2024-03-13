package com.tx.operating.utils;

import com.tx.operating.annotation.LogRecordAnnotation;
import com.tx.operating.constants.CommonConstants;
import com.tx.operating.expression.OperateRecordExpressionParse;
import com.tx.operating.factorys.ParseFunctionFactory;
import com.tx.operating.service.IParseFunction;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public static Map<String, Object> processBeforeExecuteFunctionTemplate(Map<String, Object> spelMap, AnnotatedElementKey methodKey, Object param) {
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        standardEvaluationContext.setVariable(param.getClass().getSimpleName(), param);
        OperateRecordExpressionParse parser = OperateRecordExpressionParse.getInstance();
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry<String, Object> temp : spelMap.entrySet()) {
            String expression = String.valueOf(temp.getValue());
            if (StringUtils.isBlank(expression)) {
                continue;
            }
            List<String> functionTemplates = TemplateParseUtil.getFunctionTemplate(expression);

            List<String> spelTemplates = TemplateParseUtil.getSpelTemplate(expression);
            for (String spel : spelTemplates) {
                String execSpel = "{#" + spel + "}";
                String value = String.valueOf(parser.parseExpression(execSpel, methodKey, standardEvaluationContext));
                expression = expression.replace(execSpel, CommonConstants.DOUBLE_AT + value);
            }

            if (!CollectionUtils.isEmpty(functionTemplates)) {
                String functionName = TemplateParseUtil.getFunctionName(expression);
                String functionArgs = TemplateParseUtil.getFunctionArgs(expression);
                ParseFunctionFactory parseFunctionFactory = new ParseFunctionFactory();
                IParseFunction function = parseFunctionFactory.getFunction(functionName);
                String value = function.apply(functionArgs);
                expression = expression.replaceAll(TemplateParseUtil.FUNCTION_TEMPLATE, value);
            }
            expression = expression.replace(CommonConstants.DOUBLE_AT, "");
            resultMap.put(temp.getKey(), expression);
        }
        return resultMap;
    }


}
