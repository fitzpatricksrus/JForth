package us.cownet.jforth.corevocab.data;

import us.cownet.jforth.*;

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

	protected SimpleVocabulary constructVocabulary() {
		SimpleVocabulary v = new SimpleVocabulary();
		v.addWord(new WordArrayCreate());
		v.addWord(new WordArrayCreateSize());
		v.addWord(new WordArraySize());
		v.addWord(new WordArrayAt());
		v.addWord(new WordArrayPut());
		return v;
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
			IntVariable size = (IntVariable)context.popTemp();
			context.pushTemp(new WordArray(size.getValue()));
		}
	}

	public static class WordArrayAt extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray)context.popTemp();
			IntVariable ndx = (IntVariable)context.popTemp();
			context.pushTemp(array.values[ndx.getValue()]);
		}
	}

	public static class WordArrayPut extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray)context.popTemp();
			IntVariable ndx = (IntVariable)context.popTemp();
			Word newValue = context.popTemp();
			array.values[ndx.getValue()] = newValue;
		}
	}

	public static class WordArraySize extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordArray array = (WordArray)context.popTemp();
			context.pushTemp(new IntVariable(array.values.length));
		}
	}

}