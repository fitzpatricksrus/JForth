package us.cownet.jforth;

public class WordArray extends Word {
	public static final WordArray EMPTY = new WordArray();

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

	public Word at(int ndx) {
		return values[ndx];
	}

	public void put(int ndx, Word value) {
		values[ndx] = value;
	}

	public int size() {
		return values.length;
	}

	//--------------------------
	// Vocabulary

	public static class WordArrayCreate extends Word {
		// ( -- array )
		@Override
		public void execute(ExecutionContext context) {
			context.push(new WordArray());
		}
	}

	@Override
	protected Vocabulary constructVocabulary() {
		return super.constructVocabulary()
				.addWord(new WordArrayCreate())
				.addWord(new WordArrayCreateSize())
				.addWord(new WordArraySize())
				.addWord(new WordArraySetSize())
				.addWord(new WordArrayAt())
				.addWord(new WordArrayPut())
				.addWord(new WordArrayPullFromStack())
				.addWord(new WordArrayPushToStack());
	}

	public static class WordArrayCreateSize extends Word {
		// ( size -- array )
		@Override
		public void execute(ExecutionContext context) {
			IntegerConstant size = (IntegerConstant) context.pop();
			context.push(new WordArray(size.getValue()));
		}
	}

	public static class WordArrayAt extends Word {
		// ( array ndx -- value )
		@Override
		public void execute(ExecutionContext context) {
			IntegerConstant ndx = (IntegerConstant) context.pop();
			WordArray array = (WordArray) context.pop();
			context.push(array.values[ndx.getValue()]);
		}
	}

	public static class WordArrayPut extends Word {
		// ( value array ndx -- )
		@Override
		public void execute(ExecutionContext context) {
			IntegerConstant ndx = (IntegerConstant) context.pop();
			WordArray array = (WordArray) context.pop();
			Word newValue = context.pop();
			array.values[ndx.getValue()] = newValue;
		}
	}

	public static class WordArraySize extends Word {
		// ( array -- size )
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray) context.pop();
			context.push(new IntegerConstant(array.values.length));
		}
	}

	public static class WordArraySetSize extends Word {
		// ( array, size -- )
		@Override
		public void execute(ExecutionContext context) {
			Word newValues[] = new Word[context.popInt()];
			WordArray array = (WordArray) context.pop();
			int copySize = Math.min(newValues.length, array.values.length);
			for (int i = 0; i < copySize; i++) {
				newValues[i] = array.values[i];
			}
			array.values = newValues;
		}
	}

	public static class WordArrayPullFromStack extends Word {
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

	public static class WordArrayPushToStack extends Word {
		// ( array -- word n, word, word,..,word0)
		@Override
		public void execute(ExecutionContext context) {
			WordArray a = (WordArray) context.pop();
			for (int i = a.values.length - 1; i >= 0; i--) {
				context.push(a.values[i]);
			}
		}
	}


}
