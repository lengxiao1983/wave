package com.wave.expr.parse;

import com.sun.xml.internal.ws.resources.WsservletMessages;
import com.wave.expr.AbstractExpr;
import com.wave.expr.ExprFactory;
import com.wave.expr.imp.ColumnExpr;
import com.wave.expr.imp.ConstantExpr;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;

/**
 * @author shkstart
 * @create 2021-01-27 21:37
 */
public class ExprParseUtils {

    public static AbstractExpr parse(String exprString) {
        try {
            Expression expression = CCJSqlParserUtil.parseCondExpression(exprString);
            System.out.println(expression.toString());
            return parse(expression);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static AbstractExpr parse(Expression expression) {
        if (expression instanceof BinaryExpression) {
            BinaryExpression binaryExpression = (BinaryExpression) expression;
            String op = binaryExpression.getStringExpression();
            AbstractExpr expr = ExprFactory.get().newExprInstance(op);
            Expression expressionLeft = binaryExpression.getLeftExpression();
            Expression expressionRight = binaryExpression.getRightExpression();
            expr.getParams().add(parse(expressionLeft));
            expr.getParams().add(parse(expressionRight));
            return expr;
        } else if (expression instanceof Function) {
            Function function = (Function) expression;
            String funcName = function.getName();
            AbstractExpr expr = ExprFactory.get().newExprInstance(funcName);
            ExpressionList expressionList = function.getParameters();
            if (expressionList == null || expressionList.getExpressions() == null || expressionList.getExpressions().size() == 0) {
                return expr;
            }
            for (Expression ex : expressionList.getExpressions() ) {
                expr.getParams().add(parse(ex));
            }
            return expr;
        } else if (expression instanceof Column){
            Column column = (Column)expression;
            ColumnExpr columnExpr = new ColumnExpr();
            columnExpr.setColumnName(column.getColumnName());
            return columnExpr;
        } else if (expression instanceof DoubleValue) {
            DoubleValue value = (DoubleValue) expression;
            ConstantExpr constantExpr = new ConstantExpr();
            constantExpr.setValue(value.getValue());
            return constantExpr;
        } else if (expression instanceof StringValue) {
            StringValue value = (StringValue) expression;
            ConstantExpr constantExpr = new ConstantExpr();
            constantExpr.setValue(Double.valueOf(value.getValue()));
            return constantExpr;
        } else if (expression instanceof LongValue) {
            LongValue value = (LongValue) expression;
            ConstantExpr constantExpr = new ConstantExpr();
            constantExpr.setValue(Double.valueOf(String.valueOf(value.getValue())));
            return constantExpr;
        } else if (expression instanceof Parenthesis){
            Parenthesis parenthesis = (Parenthesis) expression;
            return parse(parenthesis.getExpression());
        }
        throw new RuntimeException();
    }

    public static void main(String[] args) throws Exception {
        String exprString = "a + b > 1.0 AND func(e,f) = 3";
        parse(exprString);
    }
}
