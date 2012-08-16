package com.tsystems.javaschool.tasks;

import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class Interpretator {

	public Vector<String> getPolishInterpret(String statement) {
		StringTokenizer st = new StringTokenizer(statement + "#", "*/+-()#",
				true);
		Vector<String> polish_record = new Vector<String>();
		Stack<String> operation_vector = new Stack<String>();
		operation_vector.push("#");
		while (st.hasMoreTokens()) {
			String cur_substr = st.nextToken();
			String last_oper = operation_vector.peek();
			int cur_prior = getPriority(cur_substr);
			int last_prior = getPriority(last_oper);
			if (cur_prior == 0) { // it's number or variable
				polish_record.add(cur_substr);
			} else if (cur_prior == 2) { // it's "("
				operation_vector.push(cur_substr);
			} else if (cur_prior == 5) { // it's ")"
				do {
					polish_record.add(operation_vector.pop());
					if (operation_vector.isEmpty())
						return null; /* if we didn't find "(" to supplement ")" */
					last_prior = getPriority(operation_vector.peek());
				} while (last_prior != 2);
				operation_vector.pop();
			} else if (last_prior >= cur_prior) {
				do {
					polish_record.add(operation_vector.pop());
					last_prior = getPriority(operation_vector.peek());
				} while ((last_prior >= cur_prior) && (cur_prior != 1));
				/* if cur_prior = 1 it's mean that we in the end of string */
			}
			if ((last_prior < cur_prior) && (cur_prior != 5)
					&& (cur_prior != 2)) {
				operation_vector.push(cur_substr);
			}
		}
		if ((!operation_vector.isEmpty()) && (operation_vector.size() > 1)) {
			do {
				polish_record.add(operation_vector.pop());
			} while (operation_vector.size() > 1);
		}
		return polish_record;
	}

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
		// in all other cases (for numbers or variables) priority is 0.
		return priority;
	}
}
