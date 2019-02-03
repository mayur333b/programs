package com.solution.stringcalculator;

import java.util.Scanner;
import java.util.Stack;

public class StringCalculator {

	private static Scanner sc;
	private static final String allowedChars = "0123456789()*/+-";
	private static final String allowedOpr = "*/+-";

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] expressions = new String[n];
		for (int i = 0; i < n; i++) {
			String expr = sc.next();
			expr = expr.trim();
			expr = expr.replace(" ", "");
			expressions[i] = expr;
		}
		for (int i = 0; i < n; i++) {
			String expression = expressions[i];
			if (!isExpressionValid(expression)) {
				System.out.println("Case #" + i + ": INVALID EXPRESSION");
			}
			String value = evaluateExpression(expression);
			System.out.println("Case #" + i + ": " + value);
		}
	}

	//To evaluate expression given as String
	//Uses infix expression evaluation
	public static String evaluateExpression(String expression) {
		char[] symbols = expression.toCharArray();
		Stack<Integer> values = new Stack<Integer>();
		Stack<Character> operators = new Stack<Character>();
		for (int i = 0; i < symbols.length; i++) {
			if (symbols[i] >= '0' && symbols[i] <= '9') {
				StringBuilder sb = new StringBuilder();
				sb.append(symbols[i]);
				while (i+1 < symbols.length && symbols[i+1] >= '0' && symbols[i+1] <= '9') {
					sb.append(symbols[++i]);
				}
				values.push(Integer.parseInt(sb.toString()));
			} else if (symbols[i] == '(') {
				operators.push('(');
			} else if (symbols[i] == ')') {
				while (operators.peek() != '(')
					values.push(performOperation(operators.pop(), values.pop(), values.pop()));
				operators.pop();
			} else if (symbols[i] == '+' || symbols[i] == '-' || symbols[i] == '*' || symbols[i] == '/') {
				while (!operators.empty() && isOfLowerPrecedence(symbols[i], operators.peek()))
					values.push(performOperation(operators.pop(), values.pop(), values.pop()));
				operators.push(symbols[i]);
			}
		}
		while (!operators.empty()) {
			values.push(performOperation(operators.pop(), values.pop(), values.pop()));
		}
		return String.valueOf(values.pop());
	}

	//Check if first operator has lower precedence
	private static boolean isOfLowerPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	//To perform mathematical operation
	private static int performOperation(char operator, int val1, int val2) {
		switch (operator) {
		case '+':
			return val1 + val2;
		case '-':
			return val2 - val1;
		case '*':
			return val1 * val2;
		case '/':
			return val2 / val1;
		}
		return 0;
	}

	//Check if expression is valid or not
	public static boolean isExpressionValid(String expression) {
		if (expression == null || expression.trim().length() < 1)
			return false;
		boolean isContainsValidChars = expression.chars()
				.allMatch(p -> allowedChars.indexOf(String.valueOf((char) p)) > -1);
		if (!isContainsValidChars)
			return false;
		if (allowedOpr.indexOf(expression.charAt(0)) > -1)
			return false;
		if (allowedOpr.indexOf(expression.charAt(expression.length() - 1)) > -1)
			return false;
		if (!isExpressionHasValidParenthesis(expression))
			return false;
		return true;
	}

	//Validate if expression does have proper Parenthesis
	public static boolean isExpressionHasValidParenthesis(String expression) {
		if (expression == null || expression.trim().length() < 1)
			return false;
		if (expression.indexOf(")") < expression.indexOf("("))
			return false;
		if (expression.lastIndexOf("(") > expression.lastIndexOf(")"))
			return false;
		if (expression.chars().filter(p -> (char) p == '(').count() != expression.chars().filter(p -> (char) p == ')')
				.count())
			return false;
		if (!expression.contains("(") && !expression.contains(")"))
			return true;
		return checkParenthesis(expression);
	}

	//To check  if parenthesis are in proper order
	private static boolean checkParenthesis(String expression) {
		Stack<Character> clist = new Stack<>();
		for (char c : expression.toCharArray()) {
			if (c == '(')
				clist.push(c);
			if (c == ')')
				clist.pop();
		}
		if (!clist.isEmpty())
			return false;
		return true;
	}

}
