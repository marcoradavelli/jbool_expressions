package jbool_expressions.rules;


import jbool_expressions.And;
import jbool_expressions.ExprUtil;
import jbool_expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class CombineAnd<K> extends Rule<And<K>, K> {

  @Override
  public Expression<K> applyInternal(And<K> and) {
    for (Expression<K> expr : and.expressions) {
      if (expr instanceof And) {
        And<K> childAnd = (And<K>) expr;

        List<Expression<K>> newChildren = new ArrayList<Expression<K>>();
        ExprUtil.addAll(newChildren, ExprUtil.allExceptMatch(and.expressions, childAnd));
        ExprUtil.addAll(newChildren, childAnd.expressions);

        return new And<K>(newChildren);
      }
    }
    return and;
  }

  @Override
  protected boolean isApply(Expression<K> input) {
    return input instanceof And;
  }
}

