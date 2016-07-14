package us.cownet.jforth;

public class VariableDataWord<T> extends DataWord<T> {
	private String name;

	public VariableDataWord(String name, T value) {
		super(value);
	}

	public void setValue(T newValue) {
		super.setValue(newValue);
	}

	public Word getSetter() {
		return new Setter();
	}

	public static class Setter<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			VariableDataWord<T> dest = (VariableDataWord<T>) context.popTemp();
			DataWord<T> value = (DataWord<T>) context.popTemp();
			dest.setValue(value.getValue());
		}
	}


}
