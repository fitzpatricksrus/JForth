package us.cownet.jforth;

public class IntegerConstant extends DataWord<Integer> {
	public static final IntegerConstant ZERO = new IntegerConstant(0);
	public static final IntegerConstant ONE = new IntegerConstant(0);

	public IntegerConstant() {
		super(0);
	}

	public IntegerConstant(int value) {
		super(value);
	}

	public String toString() {
		return Integer.toString(getValue());
	}

	//--------------------------
	// Vocabulary

	@AlternateName(name = "-")
	public static void negate(ExecutionContext context) {
		context.push(-context.popInt());
	}

	@AlternateName(name = "~")
	public static void not(ExecutionContext context) {
		context.push(~context.popInt());
	}

	@AlternateName(name = "+")
	public static void plus(ExecutionContext context) {
		context.push(context.popInt() + context.popInt());
	}

	@AlternateName(name = "-")
	public static void minus(ExecutionContext context) {
		context.push(context.popInt() - context.popInt());
	}

	@AlternateName(name = "*")
	public static void times(ExecutionContext context) {
		context.push(context.popInt() * context.popInt());
	}

	@AlternateName(name = "/")
	public static void divide(ExecutionContext context) {
		context.push(context.popInt() / context.popInt());
	}

	@AlternateName(name = "%")
	public static void mod(ExecutionContext context) {
		context.push(context.popInt() % context.popInt());
	}

	@AlternateName(name = "|")
	public static void or(ExecutionContext context) {
		context.push(context.popInt() | context.popInt());
	}

	@AlternateName(name = "&")
	public static void and(ExecutionContext context) {
		context.push(context.popInt() & context.popInt());
	}

	@AlternateName(name = "^")
	public static void xor(ExecutionContext context) {
		context.push(context.popInt() ^ context.popInt());
	}

	@AlternateName(name = "<<")
	public static void shiftLeft(ExecutionContext context) {
		context.push(context.popInt() << context.popInt());
	}

	@AlternateName(name = ">>")
	public static void shiftRight(ExecutionContext context) {
		context.push(context.popInt() >> context.popInt());
	}

	@AlternateName(name = ">")
	public static void greater(ExecutionContext context) {
		context.push((context.popInt() > context.popInt()) ? BooleanConstant.TRUE : BooleanConstant.FALSE);
	}

	@AlternateName(name = "<")
	public static void less(ExecutionContext context) {
		context.push((context.popInt() < context.popInt()) ? BooleanConstant.TRUE : BooleanConstant.FALSE);
	}

	@AlternateName(name = "==")
	public static void equals(ExecutionContext context) {
		context.push((context.popInt() == context.popInt()) ? BooleanConstant.TRUE : BooleanConstant.FALSE);
	}

	@AlternateName(name = "+1")
	public static void increment(ExecutionContext context) {
		context.push(context.popInt() + 1);
	}

	@AlternateName(name = "-1")
	public static void decrement(ExecutionContext context) {
		context.push(context.popInt() - 1);
	}

	public static void toString(ExecutionContext context) {
		context.push(Integer.toString(context.popInt()));
	}
}

