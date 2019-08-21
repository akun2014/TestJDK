package com.ownerkaka.testjdk.designpattern23.interpreter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author akun
 * @since 2019-06-28
 */
@Slf4j
public class InterpreterTest {

    @Test
    public void writer() {
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("name", "Overriden writer's name");

        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("");
    }
}