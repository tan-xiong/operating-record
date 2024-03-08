package com.tx.operating.expression.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.TypedValue;
import org.springframework.expression.common.ExpressionUtils;

/**
 * @author tanxiong
 * @date 2023/11/9 16:31
 */
public class JsonExpression implements Expression {

    private final String expression;

    public JsonExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String getExpressionString() {
        return expression;
    }

    @Override
    public Object getValue() throws EvaluationException {
        return null;
    }

    @Override
    public <T> T getValue(Class<T> desiredResultType) throws EvaluationException {
        return null;
    }

    @Override
    public Object getValue(Object rootObject) throws EvaluationException {
        return null;
    }

    @Override
    public <T> T getValue(Object rootObject, Class<T> desiredResultType) throws EvaluationException {
        return null;
    }

    @Override
    public Object getValue(EvaluationContext context) throws EvaluationException {
        // 获取当前上下文的根对象的值，并将其转换为JSON字符串
        String s = JSONObject.toJSONString(context.getRootObject().getValue());
        // 使用JSONPath表达式在JSON字符串中读取指定值
        Object value = JSONPath.read(s, expression);
        // 将读取到的值转换为指定类型，并返回
        return ExpressionUtils.convertTypedValue(context, new TypedValue(value), String.class);
    }


    @Override
    public Object getValue(EvaluationContext context, Object rootObject) throws EvaluationException {
        return null;
    }

    @Override
    public <T> T getValue(EvaluationContext context, Class<T> desiredResultType) throws EvaluationException {
        String s = JSONObject.toJSONString(context.getRootObject().getValue());
        Object value = JSONPath.read(s, expression);
        return ExpressionUtils.convertTypedValue(context, new TypedValue(value), desiredResultType);
    }

    @Override
    public <T> T getValue(EvaluationContext context, Object rootObject, Class<T> desiredResultType) throws EvaluationException {
        return null;
    }

    @Override
    public Class<?> getValueType() throws EvaluationException {
        return null;
    }

    @Override
    public Class<?> getValueType(Object rootObject) throws EvaluationException {
        return null;
    }

    @Override
    public Class<?> getValueType(EvaluationContext context) throws EvaluationException {
        return null;
    }

    @Override
    public Class<?> getValueType(EvaluationContext context, Object rootObject) throws EvaluationException {
        return null;
    }

    @Override
    public TypeDescriptor getValueTypeDescriptor() throws EvaluationException {
        return null;
    }

    @Override
    public TypeDescriptor getValueTypeDescriptor(Object rootObject) throws EvaluationException {
        return null;
    }

    @Override
    public TypeDescriptor getValueTypeDescriptor(EvaluationContext context) throws EvaluationException {
        return null;
    }

    @Override
    public TypeDescriptor getValueTypeDescriptor(EvaluationContext context, Object rootObject) throws EvaluationException {
        return null;
    }

    @Override
    public boolean isWritable(Object rootObject) throws EvaluationException {
        return false;
    }

    @Override
    public boolean isWritable(EvaluationContext context) throws EvaluationException {
        return false;
    }

    @Override
    public boolean isWritable(EvaluationContext context, Object rootObject) throws EvaluationException {
        return false;
    }

    @Override
    public void setValue(Object rootObject, Object value) throws EvaluationException {

    }

    @Override
    public void setValue(EvaluationContext context, Object value) throws EvaluationException {

    }

    @Override
    public void setValue(EvaluationContext context, Object rootObject, Object value) throws EvaluationException {

    }
}
