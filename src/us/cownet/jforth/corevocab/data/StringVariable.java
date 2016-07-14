package us.cownet.jforth.corevocab.data;

import us.cownet.jforth.SimpleVocabulary;
import us.cownet.jforth.Word;

public class StringVariable extends DataWord<String> {
	public StringVariable() {
		super("");
	}

	public StringVariable(String value) {
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
			return new BooleanVariable(v1.equals(v2));
		}
	}

	public static class StringEqualsIgnoreCase extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new BooleanVariable(v1.equalsIgnoreCase(v2));
		}
	}

	public static class StringGreaterThan extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new BooleanVariable(v1.compareTo(v2) > 0);
		}
	}

	public static class StringLessThan extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new BooleanVariable(v1.compareTo(v2) < 0);
		}
	}

	public static class StringCompareTo extends BinaryOperator<String> {
		@Override
		protected Word operate(String v1, String v2) {
			return new IntVariable(v1.compareTo(v2));
		}
	}
}
