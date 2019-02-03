package com.solution.stringcalculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTest {

	@Test
	public void testIsExpressionValid() {
		assertEquals(false, StringCalculator.isExpressionValid(null));
		assertEquals(false, StringCalculator.isExpressionValid(""));
		assertEquals(false, StringCalculator.isExpressionValid("a+b"));
		assertEquals(false, StringCalculator.isExpressionValid("+1"));
		assertEquals(false, StringCalculator.isExpressionValid("1+"));
		assertEquals(true, StringCalculator.isExpressionValid("1+2"));
	}
	
	@Test
	public void testIsExpressionHasValidParenthes() {
		assertEquals(false, StringCalculator.isExpressionHasValidParenthesis("("));
		assertEquals(false, StringCalculator.isExpressionHasValidParenthesis(")"));
		assertEquals(false, StringCalculator.isExpressionHasValidParenthesis("(3+3))"));
		assertEquals(false, StringCalculator.isExpressionHasValidParenthesis("(((3+3))"));
	}
	
	@Test
	public void testEvaluateExpression() {
		//assertEquals("3", StringCalculator.evaluateExpression("1+2"));
		//assertEquals("26", StringCalculator.evaluateExpression("19+1*7"));
		//assertEquals("140", StringCalculator.evaluateExpression("(19+1)*7"));
		assertEquals("46", StringCalculator.evaluateExpression("1+(45)"));
	}
}
