package com.tsystems.javaschool.tasks;

/**
 * Testing of the Calculator. The result of calculation will be output to the
 * console. If the input statement is invalid, the result will be 'null'.
 */
public class Calc {

	public void run(String inStatement) {
		Calculator c = new CalculatorImpl();
		System.out.println(c.evaluate(inStatement));
	}

	public static void main(String[] args) {

		/**
		 * Here some test examples.
		 * 
		 * @param inStatement
		 *            [1..3] mathematical statement containing digits, '.' (dot)
		 *            as decimal mark, parentheses, operations signs '+', '-',
		 *            '*', '/'<br>
		 *            Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
		 *            Theoretically, any inStatement can be replaced with some
		 *            input stream or something like.
		 */

		String inStatement1 = new String("(1+38)*4-5"); // The result: 151
		new Calc().run(inStatement1);

		String inStatement2 = new String("7*6/2+8.5"); // The result: 29.5
		new Calc().run(inStatement2);

		String inStatement3 = new String("-12+1//("); // The result: null
		new Calc().run(inStatement3);
	}

}
