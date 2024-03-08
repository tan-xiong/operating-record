package com.tx.operating.expression.json;

import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateAwareExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;

/**
 * @author tanxiong
 * @date 2023/11/9 16:37
 */
public class JsonExpressionParser extends TemplateAwareExpressionParser {

    private final SpelParserConfiguration configuration;


    /**
     * Create a parser with default settings.
     */
    public JsonExpressionParser() {
        this.configuration = new SpelParserConfiguration();
    }

        @Override
    protected Expression doParseExpression(String expressionString, ParserContext context) throws ParseException {
        // 重写父类方法，解析表达式字符串并返回一个表达式对象
        return new JsonExpression(expressionString);
    }

}
