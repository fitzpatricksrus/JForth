package us.cownet.jforth;

public class WordArray extends Word {
	private Word values[];

	public WordArray() {
		values = new Word[0];
	}

	public WordArray(int size) {
		values = new Word[size];
	}

	public WordArray(Word words[]) {
		values = words;
	}

	@Override
	public void execute(ExecutionContext context) {
		context.push(this);
	}

	public Word at(int ndx) {
		return values[ndx];
	}

	public void put(int ndx, Word value) {
		values[ndx] = value;
	}

	public int size() {
		return values.length;
	}

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return super.constructVocabulary()
				.addWord(new WordArrayCreate())
				.addWord(new WordArrayCreateSize())
				.addWord(new WordArraySize())
				.addWord(new WordArrayAt())
				.addWord(new WordArrayPut());
	}

	public static class WordArrayCreate extends Word {
		@Override
		public void execute(ExecutionContext context) {
			context.push(new WordArray());
		}
	}

	public static class WordArrayCreateSize extends Word {
		@Override
		public void execute(ExecutionContext context) {
			IntegerConstant size = (IntegerConstant) context.pop();
			context.push(new WordArray(size.getValue()));
		}
	}

	public static class WordArrayAt extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray) context.pop();
			IntegerConstant ndx = (IntegerConstant) context.pop();
			context.push(array.values[ndx.getValue()]);
		}
	}

	public static class WordArrayPut extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray) context.pop();
			IntegerConstant ndx = (IntegerConstant) context.pop();
			Word newValue = context.pop();
			array.values[ndx.getValue()] = newValue;
		}
	}

	public static class WordArraySize extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray) context.pop();
			context.push(new IntegerConstant(array.values.length));
		}
	}

	public static class WordArrayFillFromStack extends Word {
		// ( ...word, word, word,...,array -- )
		@Override
		public void execute(ExecutionContext context) {
			WordArray a = (WordArray) context.pop();
			int count = a.values.length;
			for (int i = 0; i < count; i++) {
				a.values[i] = context.pop();
			}
		}
	}

}
