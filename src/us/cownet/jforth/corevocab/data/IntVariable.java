package us.cownet.jforth.corevocab.data;

import us.cownet.jforth.ExecutionContext;
import us.cownet.jforth.SimpleVocabulary;
import us.cownet.jforth.Word;

public class IntVariable extends Word {
	private int value;

	public IntVariable() {
		value = 0;
	}

	public IntVariable(int value) {
		this.value = value;
	}

	public void execute(ExecutionContext context) {
		context.pushTemp(this);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int newValue) {
		value = newValue;
	}

	public Word getSetter() {
		return new IntSave();
	}

	public SimpleVocabulary getVocabulary() {
		SimpleVocabulary v = new SimpleVocabulary();
		v.addWord(new IntNegate());
		v.addWord(new IntNot());
		v.addWord(new IntPlus());
		v.addWord(new IntMinus());
		v.addWord(new IntTimes());
		v.addWord(new IntDivide());
		v.addWord(new IntMod());
		v.addWord(new IntOr());
		v.addWord(new IntAnd());
		v.addWord(new IntXor());
		v.addWord(new IntShift());
		v.addWord(new IntSave());
		return v;
	}

	public static class IntSave extends Word {
		public void execute(ExecutionContext context) {
			IntVariable dest = (IntVariable) context.popTemp();
			IntVariable value = (IntVariable) context.popTemp();
			dest.value = value.value;
		}
	}

	public static abstract class UnaryOperator extends Word {
		public void execute(ExecutionContext context) {
			IntVariable v = (IntVariable) context.popTemp();
			context.pushTemp(new IntVariable(operate(v.value)));
		}

		protected abstract int operate(int value);
	}

	public static class IntNegate extends UnaryOperator {
		protected int operate(int value) {
			return -value;
		}
	}

	public static class IntNot extends UnaryOperator {
		protected int operate(int value) {
			return ~value;
		}
	}

	public static abstract class BinaryOperator extends Word {
		public void execute(ExecutionContext context) {
			IntVariable v1 = (IntVariable) context.popTemp();
			IntVariable v2 = (IntVariable) context.popTemp();
			context.pushTemp(new IntVariable(operate(v1.value, v2.value)));
		}

		protected abstract int operate(int v1, int v2);
	}

	public static class IntPlus extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 + v2;
		}
	}

	public static class IntMinus extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 - v2;
		}
	}

	public static class IntTimes extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 * v2;
		}
	}

	public static class IntDivide extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 / v2;
		}
	}

	public static class IntMod extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 % v2;
		}
	}

	public static class IntOr extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 | v2;
		}
	}

	public static class IntAnd extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 & v2;
		}
	}

	public static class IntXor extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 ^ v2;
		}
	}

	public static class IntShift extends BinaryOperator {
		protected int operate(int v1, int v2) {
			return v1 << v2;
		}
	}
}

