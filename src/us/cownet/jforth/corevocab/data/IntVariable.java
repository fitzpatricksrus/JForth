package us.cownet.jforth.corevocab.data;

import us.cownet.jforth.SimpleVocabulary;
import us.cownet.jforth.Word;

public class IntVariable extends DataWord<Integer> {
	public static final IntConstant ZERO = new IntConstant(0);
	public static final IntConstant ONE = new IntConstant(1);

	public static class IntConstant extends IntVariable {
		public IntConstant(Integer value) {
			super(value);
		}
		@Override
		public void setValue(Integer newValue) {
			throw new UnsupportedOperationException();
		}
	}

	public IntVariable() {
		super(0);
	}

	public IntVariable(int value) {
		super(value);
	}

	@Override
	public SimpleVocabulary constructVocabulary() {
		return super.constructVocabulary()
				.addWord(new IntNegate())
				.addWord(new IntNot())
				.addWord(new IntPlus())
				.addWord(new IntMinus())
				.addWord(new IntTimes())
				.addWord(new IntDivide())
				.addWord(new IntMod())
				.addWord(new IntOr())
				.addWord(new IntAnd())
				.addWord(new IntXor())
				.addWord(new IntShift())
				.addWord(new IntGreaterThan())
				.addWord(new IntLessThan())
				.addWord(new IntEquals())
				.addWord(new IntPlusPlus());
	}

	public static class IntNegate extends UnaryOperator<Integer> {
		protected Word operate(Integer value) {
			return new IntVariable(-value);
		}
	}

	public static class IntNot extends UnaryOperator<Integer> {
		protected Word operate(Integer value) {
			return new IntVariable(~value);
		}
	}

	public static class IntPlus extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 + v2);
		}
	}

	public static class IntMinus extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 - v2);
		}
	}

	public static class IntTimes extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 * v2);
		}
	}

	public static class IntDivide extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 / v2);
		}
	}

	public static class IntMod extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 % v2);
		}
	}

	public static class IntOr extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 | v2);
		}
	}

	public static class IntAnd extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 & v2);
		}
	}

	public static class IntXor extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 ^ v2);
		}
	}

	public static class IntShift extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new IntVariable(v1 << v2);
		}
	}

	public static class IntGreaterThan extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new BooleanVariable(v1 > v2);
		}
	}

	public static class IntLessThan extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new BooleanVariable(v1 < v2);
		}
	}

	public static class IntEquals extends BinaryOperator<Integer> {
		protected Word operate(Integer v1, Integer v2) {
			return new BooleanVariable(v1 == v2);
		}
	}

	public static class IntPlusPlus extends UnaryOperator<Integer> {
		protected Word operate(Integer v) {
			return new IntVariable(v+1);
		}
	}

}

