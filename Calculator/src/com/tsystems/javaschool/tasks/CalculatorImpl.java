package com.tsystems.javaschool.tasks;

import java.util.Stack;
import java.util.Vector;

/**
 * The implementation of Calculator interface. Evaluate statement represented as
 * string.
 */
public class CalculatorImpl implements Calculator {

	/*
	 * @see com.tsystems.javaschool.tasks.Calculator #evaluate(java.lang.String)
	 */
	@Override
	public String evaluate(String statement) {
		String result = new String();
		Interpretator interpretator = new Interpretator();
		Vector<String> polish_record = interpretator
				.getPolishInterpret(statement);
		if (polish_record == null) return null;
		result = calculate(polish_record);
		return result;
	}

	private String calculate(Vector<String> polish_record) {
		Stack<Double> calculations = new Stack<Double>();
		for (int i = 0; i < polish_record.size(); i++) {
			String cur_symbol = polish_record.get(i);
			if (cur_symbol.equals("+")) {
				if (calculations.size() > 1) {
					double value1 = calculations.pop();
					double value2 = calculations.pop();
					calculations.push(value2 + value1);
				} else {
					return null;
				}
			} else if (cur_symbol.equals("-")) {
				if (calculations.size() > 1) {
					double value1 = calculations.pop();
					double value2 = calculations.pop();
					calculations.push(value2 - value1);
				} else {
					return null;
				}
			} else if (cur_symbol.equals("*")) {
				if (calculations.size() > 1) {
					double value1 = calculations.pop();
					double value2 = calculations.pop();
					calculations.push(value2 * value1);
				} else {
					return null;
				}
			} else if (cur_symbol.equals("/")) {
				if (calculations.size() > 1) {
					double value1 = calculations.pop();
					double value2 = calculations.pop();
					calculations.push(value2 / value1);
				} else {
					return null;
				}
			} else {
				try {
					double value = Double.parseDouble(cur_symbol);
					calculations.push(value);
				} catch (Exception e) {
					return null;
				}
			}
		}
		if (calculations.size() == 1){
			double result = calculations.pop();
			StringBuilder sb = new StringBuilder();
			sb.append(result);
			String str_result = sb.toString(); 
			return str_result;
		}
		return null;
	}
}
