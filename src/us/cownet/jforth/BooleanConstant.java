package us.cownet.jforth;

public class BooleanConstant extends DataWord<Boolean> {
	public static final BooleanConstant TRUE = new BooleanConstant(true);
	public static final BooleanConstant FALSE = new BooleanConstant(false);

	protected BooleanConstant(boolean value) {
		super(value);
	}

	private static boolean pop(ExecutionContext context) {
		return ((DataWord<Boolean>) context.pop()).getValue();
	}

	//--------------------------
	// Vocabulary

	@AlternateName(name = "!")
	public static void not(ExecutionContext context) {
		context.push((!pop(context)) ? TRUE : FALSE);
	}

	@AlternateName(name = "==")
	public static void equals(ExecutionContext context) {
		boolean v1 = pop(context);
		boolean v2 = pop(context);
		context.push((v1 == v2) ? TRUE : FALSE);
	}

	@AlternateName(name = "!=")
	public static void notEquals(ExecutionContext context) {
		boolean v1 = pop(context);
		boolean v2 = pop(context);
		context.push((v1 != v2) ? TRUE : FALSE);
	}

	@AlternateName(name = "||")
	public static void or(ExecutionContext context) {
		boolean v1 = pop(context);
		boolean v2 = pop(context);
		context.push((v1 || v2) ? TRUE : FALSE);
	}

	@AlternateName(name = "&&")
	public static void and(ExecutionContext context) {
		boolean v1 = pop(context);
		boolean v2 = pop(context);
		context.push((v1 && v2) ? TRUE : FALSE);
	}

	@AlternateName(name = "^")
	public static void xor(ExecutionContext context) {
		boolean v1 = pop(context);
		boolean v2 = pop(context);
		context.push((v1 ^ v2) ? TRUE : FALSE);
	}
}
