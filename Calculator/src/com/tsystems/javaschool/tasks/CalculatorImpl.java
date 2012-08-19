package com.tsystems.javaschool.tasks;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
		/** The result of calculation */
		String result = new String();
		Interpretator interpretator = new Interpretator();

		/** The polish record of input statement */
		Vector<String> polish_record = interpretator
				.getPolishInterpret(statement);

		/** If input statement were incorrect, polish record will be null */
		if (polish_record == null)
			return null;
		result = calculate(polish_record);
		return result;
	}

	/**
	 * Calculates input statement presented in the form of the polish record.
	 * This method sequentially reads the symbols of the record, executing one
	 * command for each symbol. If the symbol is a number, it pushed on the
	 * stack; if operation - execute this operation on numbers from the stack
	 * and push the result instead.
	 * 
	 * @param polish_record
	 *            Input statement presented in the form of the polish record
	 * @return result of calculation in the String form
	 */
	private String calculate(Vector<String> polish_record) {

		/** The stack for numbers */
		Stack<Double> calculations = new Stack<Double>();

		for (int i = 0; i < polish_record.size(); i++) {
			/** The symbol under consideration */
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
		/** Output the result after reach the end of record */
		if (calculations.size() == 1) {
			double result = calculations.pop();
			double short_result = new BigDecimal(result).setScale(4,
					RoundingMode.UP).doubleValue();
			StringBuilder sb = new StringBuilder();
			sb.append(short_result);
			String str_result = sb.toString();
			return str_result;
		}
		return null;
	}
}
