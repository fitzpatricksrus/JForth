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

	public static void create(ExecutionContext context) {
		// ( -- array )
		context.push(new WordArray());
	}

	@AlternateName(name = "createSized:")
	public static void createSized(ExecutionContext context) {
		// ( size -- array )
		IntegerConstant size = (IntegerConstant) context.pop();
		context.push(new WordArray(size.getValue()));
	}

	@AlternateName(name = "at:")
	public static void at(ExecutionContext context) {
		// ( array ndx -- value )
		IntegerConstant ndx = (IntegerConstant) context.pop();
		WordArray array = (WordArray) context.pop();
		context.push(array.values[ndx.getValue()]);
	}

	@AlternateName(name = "put:")
	public static void put(ExecutionContext context) {
		// ( value array ndx -- )
		IntegerConstant ndx = (IntegerConstant) context.pop();
		WordArray array = (WordArray) context.pop();
		Word newValue = context.pop();
		array.values[ndx.getValue()] = newValue;
	}

	public static void size(ExecutionContext context) {
		// ( array -- size )
		WordArray array = (WordArray) context.pop();
		context.push(new IntegerConstant(array.values.length));
	}

	@AlternateName(name = "setSize:")
	public static void setSize(ExecutionContext context) {
		// ( array, size -- )
		Word newValues[] = new Word[context.popInt()];
		WordArray array = (WordArray) context.pop();
		int copySize = Math.min(newValues.length, array.values.length);
		for (int i = 0; i < copySize; i++) {
			newValues[i] = array.values[i];
		}
		array.values = newValues;
	}

	@AlternateName(name = "pullFromStack")
	public static void WordArrayPullFromStack(ExecutionContext context) {
		// ( ...word, word, word,...,array -- )
		WordArray a = (WordArray) context.pop();
		int count = a.values.length;
		for (int i = 0; i < count; i++) {
			a.values[i] = context.pop();
		}
	}

	@AlternateName(name = "pushToStack")
	public static void WordArrayPushToStack(ExecutionContext context) {
		// ( array -- word n, word, word,..,word0)
		WordArray a = (WordArray) context.pop();
		for (int i = a.values.length - 1; i >= 0; i--) {
			context.push(a.values[i]);
		}
	}
}
