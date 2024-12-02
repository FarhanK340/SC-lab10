package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionTest {

    @Test
    public void testParse_simpleConstant() {
        Expression expr = Expression.parse("5");
        assertEquals("5.0", expr.toString());
    }

    @Test
    public void testParse_simpleVariable() {
        Expression expr = Expression.parse("x");
        assertEquals("x", expr.toString());
    }

    @Test
    public void testParse_simpleAddition() {
        Expression expr = Expression.parse("x + 3");
        assertEquals("(x + 3.0)", expr.toString());
    }

    @Test
    public void testParse_simpleMultiplication() {
        Expression expr = Expression.parse("x * 3");
        assertEquals("(x * 3.0)", expr.toString());
    }

    @Test
    public void testParse_nestedExpression() {
        Expression expr = Expression.parse("x + 3 * y");
        assertEquals("(x + (3.0 * y))", expr.toString());
    }

    @Test
    public void testEquals_sameExpressions() {
        Expression expr1 = Expression.parse("x + 3");
        Expression expr2 = Expression.parse("x + 3");
        assertTrue(expr1.equals(expr2));
        assertEquals(expr1.hashCode(), expr2.hashCode());
    }

    @Test
    public void testEquals_differentExpressions() {
        Expression expr1 = Expression.parse("x + 3");
        Expression expr2 = Expression.parse("x * 3");
        assertFalse(expr1.equals(expr2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParse_invalidExpression() {
        Expression.parse("x ++ 3");
    }

    @Test
    public void testHashCode_consistentForSameExpression() {
        Expression expr = Expression.parse("x + 3");
        int hash1 = expr.hashCode();
        int hash2 = expr.hashCode();
        assertEquals(hash1, hash2);
    }

}
