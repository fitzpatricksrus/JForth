package us.cownet.jforth.corevocab.data;

import us.cownet.jforth.SimpleVocabulary;
import us.cownet.jforth.Word;

public class BooleanVariable extends DataWord<Boolean> {
	public static final BooleanVariable TRUE = new BooleanVariable(true) {
		public void setValue(Boolean newValue) {
			throw new UnsupportedOperationException();
		}
	};

	public static final BooleanVariable FALSE = new BooleanVariable(false) {
		public void setValue(Boolean newValue) {
			throw new UnsupportedOperationException();
		}
	};

	public BooleanVariable() {
		super(false);
	}

	public BooleanVariable(boolean value) {
		super(value);
	}

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return super.constructVocabulary()
				.addWord(new BooleanNot())
				.addWord(new BooleanEquals())
				.addWord(new BooleanNotEquals());
	}

	public static class BooleanNot extends UnaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean value) {
			return new BooleanVariable(!value);
		}
	}

	public static class BooleanEquals extends BinaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean v1, Boolean v2) {
			return (v1 == v2) ? TRUE : FALSE;
		}
	}

	public static class BooleanNotEquals extends BinaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean v1, Boolean v2) {
			return (v1 == v2) ? TRUE : FALSE;
		}
	}
}
