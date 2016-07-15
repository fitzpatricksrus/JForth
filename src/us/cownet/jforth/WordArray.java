package us.cownet.jforth;

public class WordArray extends Word {
	private Word values[];

	public WordArray() {
		values = new Word[0];
	}

	public WordArray(int size) {
		values = new Word[size];
	}

	public void execute(ExecutionContext context) {
		context.pushTemp(this);
	}

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return constructVocabulary()
				.addWord(new WordArrayCreate())
				.addWord(new WordArrayCreateSize())
				.addWord(new WordArraySize())
				.addWord(new WordArrayAt())
				.addWord(new WordArrayPut());
	}

	public static class WordArrayCreate extends Word {
		@Override
		public void execute(ExecutionContext context) {
			context.pushTemp(new WordArray());
		}
	}

	public static class WordArrayCreateSize extends Word {
		@Override
		public void execute(ExecutionContext context) {
			IntegerConstant size = (IntegerConstant) context.popTemp();
			context.pushTemp(new WordArray(size.getValue()));
		}
	}

	public static class WordArrayAt extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray)context.popTemp();
			IntegerConstant ndx = (IntegerConstant) context.popTemp();
			context.pushTemp(array.values[ndx.getValue()]);
		}
	}

	public static class WordArrayPut extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray)context.popTemp();
			IntegerConstant ndx = (IntegerConstant) context.popTemp();
			Word newValue = context.popTemp();
			array.values[ndx.getValue()] = newValue;
		}
	}

	public static class WordArraySize extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray)context.popTemp();
			context.pushTemp(new IntegerConstant(array.values.length));
		}
	}

}