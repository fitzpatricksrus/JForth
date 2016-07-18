package us.cownet.jforth;

public class BooleanConstant extends DataWord<Boolean> {
	public static final BooleanConstant TRUE = new BooleanConstant(true);
	public static final BooleanConstant FALSE = new BooleanConstant(false);

	protected BooleanConstant(boolean value) {
		super(value);
	}

	//--------------------------
	// Vocabulary

	@Override
	protected Vocabulary constructVocabulary() {
		return super.constructVocabulary()
		            .addWord(new BooleanNot())
		            .addWord(new BooleanEquals())
		            .addWord(new BooleanNotEquals())
		            .addWord(new BooleanAnd())
		            .addWord(new BooleanOr())
		            .addWord(new BooleanXor());
	}

	public static class BooleanNot extends UnaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean value) {
			// ( BooleanConstant - BooleanConstant )
			return (!value) ? TRUE : FALSE;
		}
	}

	public static class BooleanEquals extends BinaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean v1, Boolean v2) {
			// ( BooleanConstant, BooleanConstant - BooleanConstant )
			return (v1 == v2) ? TRUE : FALSE;
		}
	}

	public static class BooleanNotEquals extends BinaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean v1, Boolean v2) {
			// ( BooleanConstant, BooleanConstant - BooleanConstant )
			return (v1 == v2) ? TRUE : FALSE;
		}
	}

	public static class BooleanOr extends BinaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean v1, Boolean v2) {
			// ( BooleanConstant, BooleanConstant - BooleanConstant )
			return (v1 || v2) ? TRUE : FALSE;
		}
	}

	public static class BooleanAnd extends BinaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean v1, Boolean v2) {
			// ( BooleanConstant, BooleanConstant - BooleanConstant )
			return (v1 && v2) ? TRUE : FALSE;
		}
	}

	public static class BooleanXor extends BinaryOperator<Boolean> {
		@Override
		protected Word operate(Boolean v1, Boolean v2) {
			// ( BooleanConstant, BooleanConstant - BooleanConstant )
			return (v1 ^ v2) ? TRUE : FALSE;
		}
	}
}
