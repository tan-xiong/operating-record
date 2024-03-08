package com.tx.operating.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author tanxiong
 * @date 2023/11/9 17:21
 */
public class ConvertJsonObjUtil {

        /**
     * 将对象转换为JSON对象
     * @param obj 要转换的对象
     * @return 转换后的JSON对象
     * @throws IllegalAccessException 如果在访问字段时发生异常
     */
    public static JSONObject convertJsonObject(Object obj) throws IllegalAccessException {
        // 不要改成 JSON.parseObject(JSON.toJSONString(row, SerializerFeature.WriteEnumUsingToString)); 性能差5-10倍
        if (obj == null) {
            return new JSONObject();
        }
        JSONObject jsonObject = new JSONObject();
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            Object value = FieldUtils.readField(field, obj, true);
            if (field.getType() == String.class || field.getType() == Date.class || field.getType() == BigDecimal.class) {
                jsonObject.put(field.getName(), value);
            } else if (ClassUtils.isPrimitiveOrWrapper(field.getType())) {
                jsonObject.put(field.getName(), value);
            } else if (field.getType().isEnum()) {
                jsonObject.put(field.getName(), Objects.nonNull(value) ? ((Enum<?>) value).name() : null);
            } else if (field.getType().getClassLoader() != null) {
                jsonObject.put(field.getName(), convertJsonObject(value));
            } else {
                jsonObject.put(field.getName(), value);
            }
        }
        return jsonObject;
    }

}
