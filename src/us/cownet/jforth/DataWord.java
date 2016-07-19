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
}
