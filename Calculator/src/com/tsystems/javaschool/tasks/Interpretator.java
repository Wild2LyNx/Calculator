package com.tsystems.javaschool.tasks;

import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This is the class for interpretation of input statement in standard form into
 * form which is more suitable.
 */
public class Interpretator {

	/**
	 * Interpretating method, returns polish record of statement.
	 * 
	 * This method iterates through each symbol (simple operands (i.e. numbers)
	 * or operations, including '(' and ')' ).
	 * 
	 * If the next symbol is a number, then place it in output string (polish
	 * record);
	 * 
	 * if operation symbol, than check the priority of this operation: '*' and
	 * '/' has priority value 4, '+' and '-' - 3. '(' has priority value 2, ')'
	 * - 5; auxiliary symbol of start and end of statement - 1. After checking
	 * of priority, we should check operation stack, more precisely - previous
	 * operation. There are two possibilities: a) if the symbols in the stack
	 * have lower than current symbol priority, then put in in the stack too; b)
	 * in another case, if the symbol at the top of the stack is higher
	 * priority, then pop the symbols from the stack into output string (polish
	 * record) until condition is satisfied. Then return to step a);
	 * 
	 * if the next symbol is '(', then put it in the stack; if ')', then
	 * retrieve elements from the operation_stack until reception of ')'. Then
	 * just throw out both '(' and ')'.
	 * 
	 * If all input string disassembled, and in the stack still signs of operation,
	 * extract them from the stack to the output string.
	 * 
	 * @param statement
	 *            input statement, in standard form.
	 * @return the same statement in polish record form
	 */
	public Vector<String> getPolishInterpret(String statement) {

		/** Fragmentation of input statement into operations and operands */
		StringTokenizer st = new StringTokenizer(statement + "#", "*/+-()#",
				true);

		Vector<String> polish_record = new Vector<String>();

		/**
		 * Auxiliary stack for temporary holding of operations ('+', '-', '*',
		 * '/')
		 */
		Stack<String> operation_stack = new Stack<String>();
		operation_stack.push("#"); // auxiliary symbol for designation of the
									// start of the statement
		while (st.hasMoreTokens()) {
			String cur_substr = st.nextToken();
			String last_oper = operation_stack.peek();
			int cur_prior = getPriority(cur_substr);
			int last_prior = getPriority(last_oper);
			if (cur_prior == 0) { // it's number
				polish_record.add(cur_substr);
			} else if (cur_prior == 2) { // it's "("
				operation_stack.push(cur_substr);
			} else if (cur_prior == 5) { // it's ")"
				do {
					polish_record.add(operation_stack.pop());
					if (operation_stack.isEmpty())
						return null; /* if we didn't find "(" to supplement ")" */
					last_prior = getPriority(operation_stack.peek());
				} while (last_prior != 2);
				operation_stack.pop();
			} else if (last_prior >= cur_prior) {
				do {
					polish_record.add(operation_stack.pop());
					last_prior = getPriority(operation_stack.peek());
				} while ((last_prior >= cur_prior) && (cur_prior != 1));
				/* if cur_prior = 1 it's mean that we in the end of string */
			}
			if ((last_prior < cur_prior) && (cur_prior != 5)
					&& (cur_prior != 2)) {
				operation_stack.push(cur_substr);
			}
		}
		if ((!operation_stack.isEmpty()) && (operation_stack.size() > 1)) {
			do {
				polish_record.add(operation_stack.pop());
			} while (operation_stack.size() > 1);
		}
		return polish_record;
	}

	/**Prioritization for all symbols*/
	private int getPriority(String cur_substr) {
		int priority = 0;

		if (cur_substr.equals("*") | cur_substr.equals("/"))
			priority = 4;
		else if (cur_substr.equals("+") | cur_substr.equals("-"))
			priority = 3;
		else if (cur_substr.equals("("))
			priority = 2;
		else if (cur_substr.equals(")"))
			priority = 5;
		else if (cur_substr.equals("#"))
			priority = 1;
		// in all other cases (for numbers) priority is 0.
		return priority;
	}
}
