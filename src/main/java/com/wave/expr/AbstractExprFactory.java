package com.wave.expr;

/**
 * @author shkstart
 * @create 2021-01-27 21:46
 */
public abstract class AbstractExprFactory<T extends AbstractExpr> {

    public abstract T newInstance();
}
