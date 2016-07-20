package us.cownet.jforth;

public class StringConstant extends DataWord<String> {
	public StringConstant() {
		super("");
	}

	public StringConstant(String value) {
		super(value);
	}

	public String toString() {
		return getValue();
	}

	//--------------------------
	// Vocabulary

	@AlternateName(name = "==")
	public static void equal(ExecutionContext context) {
		context.push(context.popString().equals(context.popString()));

	}

	@AlternateName(name = "~=")
	public static void equalIgnoreCase(ExecutionContext context) {
		context.push(context.popString().equalsIgnoreCase(context.popString()));

	}

	@AlternateName(name = ">")
	public static void greater(ExecutionContext context) {
		context.push(context.popString().compareTo(context.popString()) > 0);

	}

	@AlternateName(name = "<")
	public static void less(ExecutionContext context) {
		context.push(context.popString().compareTo(context.popString()) < 0);

	}

	public static void compareTo(ExecutionContext context) {
		context.push(context.popString().compareTo(context.popString()));

	}

	public static void concat(ExecutionContext context) {
		context.push(context.popString() + context.popString());

	}

	public static void substring(ExecutionContext context) {
		// ( string startInclusive endExclusive -- string )
		int end = context.popInt();
		int start = context.popInt();
		String s = context.popString();
		context.push(s.substring(start, end));
	}
}
