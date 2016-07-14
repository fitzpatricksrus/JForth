package us.cownet.jforth;

public class StringConstant extends DataWord<String> {
	public StringConstant() {
		super("");
	}

	public StringConstant(String value) {
		super(value);
	}

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return super.constructVocabulary()
				.addWord(new StringEquals())
				.addWord(new StringEqualsIgnoreCase())
				.addWord(new StringGreaterThan())
				.addWord(new StringLessThan())
				.addWord(new StringCompareTo());
	}

	public static class StringEquals extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new BooleanConstant(v1.equals(v2));
		}
	}

	public static class StringEqualsIgnoreCase extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new BooleanConstant(v1.equalsIgnoreCase(v2));
		}
	}

	public static class StringGreaterThan extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new BooleanConstant(v1.compareTo(v2) > 0);
		}
	}

	public static class StringLessThan extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new BooleanConstant(v1.compareTo(v2) < 0);
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
}
