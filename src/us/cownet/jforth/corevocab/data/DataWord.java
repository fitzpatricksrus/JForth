package us.cownet.jforth.corevocab.data;

import us.cownet.jforth.ExecutionContext;
import us.cownet.jforth.SimpleVocabulary;
import us.cownet.jforth.Word;

public abstract class DataWord<T> extends Word {
	private T value;

	public DataWord(T value) {
		this.value = value;
	}

	public void execute(ExecutionContext context) {
		context.pushTemp(this);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T newValue) {
		value = newValue;
	}

	public Word getSetter() {
		return new Setter();
	}

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return super.constructVocabulary().addWord(new IdentityEquals<>());
	}

	public static class IdentityEquals<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			context.pushTemp(new BooleanVariable(context.popTemp() == context.popTemp()));
		}
	}

	public static class Setter<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			DataWord<T> dest = (DataWord<T>) context.popTemp();
			DataWord<T> value = (DataWord<T>) context.popTemp();
			dest.value = value.value;
		}
	}

	public static abstract class UnaryOperator<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			DataWord<T> v = (DataWord<T>) context.popTemp();
			context.pushTemp(operate(v.value));
		}

		protected abstract Word operate(T value);
	}

	public static abstract class BinaryOperator<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			DataWord<T> v1 = (DataWord<T>) context.popTemp();
			DataWord<T> v2 = (DataWord<T>) context.popTemp();
			context.pushTemp(operate(v1.value, v2.value));
		}

		protected abstract Word operate(T v1, T v2);
	}

}
