package us.cownet.jforth;

public abstract class DataWord<T> extends Word {
	private T value;

	public DataWord(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	protected void setValue(T newValue) {
		value = newValue;
	}

	public static abstract class UnaryOperator<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			DataWord<T> v = (DataWord<T>) context.pop();
			context.push(operate(v.value));
		}

		protected abstract Word operate(T value);
	}

	public static abstract class BinaryOperator<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			DataWord<T> v1 = (DataWord<T>) context.pop();
			DataWord<T> v2 = (DataWord<T>) context.pop();
			context.push(operate(v1.value, v2.value));
		}

		protected abstract Word operate(T v1, T v2);
	}

}
