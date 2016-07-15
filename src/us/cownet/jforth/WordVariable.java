package us.cownet.jforth;

public class WordVariable extends Word {
	private Word value;

	public static final WordVariable NULL = new WordVariable(null);

	public WordVariable(Word value) {
		this.value = value;
	}

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return constructVocabulary()
				.addWord(new WordVariable.WordVariableCreate())
				.addWord(new WordVariable.WordVariableAt())
				.addWord(new WordVariable.WordVariablePut());
	}

	public static class WordVariableCreate extends Word {
		@Override
		public void execute(ExecutionContext context) {
			context.push(new WordVariable(NULL));
		}
	}

	public static class WordVariableAt extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordVariable array = (WordVariable) context.pop();
			IntegerConstant ndx = (IntegerConstant) context.pop();
			context.push(array.value);
		}
	}

	public static class WordVariablePut extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordVariable array = (WordVariable) context.pop();
			IntegerConstant ndx = (IntegerConstant) context.pop();
			Word newValue = context.pop();
			array.value = newValue;
		}
	}
}
