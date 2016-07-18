package us.cownet.jforth;

public class StringConstant extends DataWord<String> {
	public StringConstant() {
		super("");
	}

	public StringConstant(String value) {
		super(value);
	}

	//--------------------------
	// Vocabulary

	public static class StringEquals extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return (v1.equals(v2)) ? BooleanConstant.TRUE : BooleanConstant.FALSE;
		}
	}

	@Override
	protected Vocabulary constructVocabulary() {
		return super.constructVocabulary()
				.addWord(new StringEquals())
				.addWord(new StringEqualsIgnoreCase())
				.addWord(new StringGreaterThan())
				.addWord(new StringLessThan())
				.addWord(new StringCompareTo())
				.addWord(new StringConcat());
	}

	public static class StringEqualsIgnoreCase extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return (v1.equalsIgnoreCase(v2)) ? BooleanConstant.TRUE : BooleanConstant.FALSE;
		}
	}

	public static class StringGreaterThan extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return (v1.compareTo(v2) > 0) ? BooleanConstant.TRUE : BooleanConstant.FALSE;
		}
	}

	public static class StringLessThan extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return (v1.compareTo(v2) < 0) ? BooleanConstant.TRUE : BooleanConstant.FALSE;
		}
	}

	public static class StringCompareTo extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new IntegerConstant(v1.compareTo(v2));
		}
	}

	public static class StringConcat extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new StringConstant(v1 + v2);
		}
	}

	public static class StringSubString extends Word {
		// ( string startInclusive endExclusive -- string )
		@Override
		public void execute(ExecutionContext context) {
			int end = context.popInt();
			int start = context.popInt();
			String s = context.popString();
			String subString = s.substring(start, end);
			context.push(subString);
		}
	}


}
