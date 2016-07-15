package us.cownet.jforth;

public class BooleanConstant extends DataWord<Boolean> {
	public static final BooleanConstant TRUE = new BooleanConstant(true);
	public static final BooleanConstant FALSE = new BooleanConstant(false);

	public BooleanConstant() {
		super(false);
	}

	public BooleanConstant(boolean value) {
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
			return (!value) ? TRUE : FALSE;
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
