package com.tx.operating.aspect;

import com.tx.operating.annotation.LogRecordAnnotation;
import com.tx.operating.constants.CommonConstants;
import com.tx.operating.exception.OrsRuntimeException;
import com.tx.operating.spi.KeepOperatingRecordSpi;
import com.tx.operating.utils.LogRecordOperationSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author tanxiong
 * @date 2023/1/28 14:41
 */
@Slf4j
@Aspect
@Component
public class LogRecordAspect {

    @Pointcut("@annotation(com.tx.operating.annotation.LogRecordAnnotation)")
    private void method() {
    }

    @Around("method()")
    public Object divAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, Object> resultMap = new HashMap<>();
        Object proceed = null;

        try {
            //获取注解信息
            LogRecordAnnotation annotation = LogRecordOperationSource.getAnnotation(joinPoint);
            //获取SPEL表达式
            Map<String, Object> spelMap = LogRecordOperationSource.getBeforeExecuteFunctionTemplate(annotation);
            //执行SPEL表达式和执行自定义函数
            AnnotatedElementKey methodKey=new AnnotatedElementKey(((MethodSignature) joinPoint.getSignature()).getMethod(),joinPoint.getTarget().getClass());
            resultMap = LogRecordOperationSource.processBeforeExecuteFunctionTemplate(spelMap,methodKey,joinPoint.getArgs()[0]);
        } catch (Exception e) {
           log.info("/// log record exec error",e);
        }

        try {
            proceed = joinPoint.proceed();
        } catch (Exception e) {
            //目标方法执行异常，设置操作状态为失败
            resultMap.put(CommonConstants.SUCCEED,false);
            throw new OrsRuntimeException("//// 目标方法执行异常,"+ e.getMessage());
        }finally {
            ServiceLoader<KeepOperatingRecordSpi> loader = ServiceLoader.load(KeepOperatingRecordSpi.class);
            Iterator<KeepOperatingRecordSpi> it = loader.iterator();
            if (it.hasNext()) {
                KeepOperatingRecordSpi keepOperatingRecordSpi = it.next();
                keepOperatingRecordSpi.keepRecord(resultMap);
            }
        }

        return proceed;
    }


}
