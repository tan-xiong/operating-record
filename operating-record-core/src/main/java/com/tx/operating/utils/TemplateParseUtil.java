package com.tx.operating.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tanxiong
 * @date 2024/3/12 14:10
 */
@Slf4j
public class TemplateParseUtil {

    public static final String FUNCTION_TEMPLATE = "`\\{(.+?)\\}`";
    private static final String FUNCTION_NAME = "(\\w+)@@";
    private static final String FUNCTION_ARGS = "@@([^}]+)";
    private static final String SPEL_TEMPLATE = "\\{#([^}]+)\\}";

    public static List<String> getFunctionTemplate(String str) {
        List<String> functionTemplates = new ArrayList<>();
        Pattern pattern = Pattern.compile(FUNCTION_TEMPLATE);
        Matcher matcher = pattern.matcher(str);
        // 循环匹配，并输出结果
        while (matcher.find()) {
            String templateContent = matcher.group(1);
            functionTemplates.add(templateContent);
        }
        return functionTemplates;
    }

    public static String getFunctionName(String str) {
        Pattern functionNamePattern = Pattern.compile(FUNCTION_NAME);
        Matcher functionNameMatcher = functionNamePattern.matcher(str);
        if (functionNameMatcher.find()) {
            return functionNameMatcher.group(1);
        }
        return null;
    }

    public static String getFunctionArgs(String str) {
        Pattern functionArgsPattern = Pattern.compile(FUNCTION_ARGS);
        Matcher functionArgsMatcher = functionArgsPattern.matcher(str);
        if (functionArgsMatcher.find()) {
            return functionArgsMatcher.group(1);
        }
        return null;
    }

    public static List<String> getSpelTemplate(String str) {
        List<String> spelTemplates = new ArrayList<>();
        Pattern pattern = Pattern.compile(SPEL_TEMPLATE);
        Matcher matcher = pattern.matcher(str);
        // 循环匹配，并输出结果
        while (matcher.find()) {
            String templateContent = matcher.group(1);
            spelTemplates.add(templateContent);
        }
        return spelTemplates;
    }


    public static void main(String[] args) {
        String str = "defaultFunction{#Example.address}";
        String str1 = "`{defu{#1223}}`";
        String str2 = "defaultFunction{#深圳}";

        List<String> template = getSpelTemplate(str);
//        log.info("/// TemplateParseUtil 执行结果" + JSON.toJSONString(template));

        List<String> template1 = getFunctionTemplate(str1);
//        log.info("/// TemplateParseUtil 执行结果" + JSON.toJSONString(template1));

        String template2 = getFunctionName(str2);
       log.info("/// TemplateParseUtil 执行结果" + JSON.toJSONString(template2));


        String template3 = getFunctionArgs(str2);
       log.info("/// TemplateParseUtil 执行结果" + JSON.toJSONString(template3));




    }


}
