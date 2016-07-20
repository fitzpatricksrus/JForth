package us.cownet.jforth;

public class BooleanConstant extends DataWord<Boolean> {
	public static final BooleanConstant TRUE = new BooleanConstant(true);
	public static final BooleanConstant FALSE = new BooleanConstant(false);

	protected BooleanConstant(boolean value) {
		super(value);
	}

	public String toString() {
		return Boolean.toString(getValue());
	}

	//--------------------------
	// Vocabulary

	@AlternateName(name = "!")
	public static void not(ExecutionContext context) {
		context.push(!context.popBoolean());
	}

	@AlternateName(name = "==")
	public static void equals(ExecutionContext context) {
		context.push(context.popBoolean() == context.popBoolean());
	}

	@AlternateName(name = "!=")
	public static void notEquals(ExecutionContext context) {
		context.push(context.popBoolean() == context.popBoolean());
	}

	@AlternateName(name = "||")
	public static void or(ExecutionContext context) {
		context.push(context.popBoolean() || context.popBoolean());
	}

	@AlternateName(name = "&&")
	public static void and(ExecutionContext context) {
		context.push(context.popBoolean() && context.popBoolean());
	}

	@AlternateName(name = "^")
	public static void xor(ExecutionContext context) {
		context.push(context.popBoolean() ^ context.popBoolean());
	}
}
