package com.wave.expr;

import com.wave.expr.imp.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-26 21:22
 */
public class ExprFactory {
    private static final Map<String, AbstractExpr> REGISTED_EXPR = new HashMap<String, AbstractExpr>();

    private static final ExprFactory instance = new ExprFactory();

    public static ExprFactory get() {
        return instance;
    }

    static {
        register(new AddExpr());
        register(new AndExpr());
        register(new BigExpr());
        register(new DivisionExpr());
        register(new MultiplicationExpr());
        register(new QYFuncExpr());
        register(new SubtractionExpr());
        register(new LessThanExpr());
        register(new EqualExpr());
        register(new OrExpr());
    }

    private static void register(AbstractExpr expr) {
        REGISTED_EXPR.put(expr.op(), expr);
    }

    /**
     * 通过表达式操作符获取表达式实例
     * @param op
     * @return
     */
    public AbstractExpr newExprInstance(String op) {
        try {
            return REGISTED_EXPR.get(op).getFactory().newInstance();
        } catch (Exception e) {
            System.out.println("op:" + op);
            throw new RuntimeException(e);
        }
    }

}
