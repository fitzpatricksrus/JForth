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
			context.pushTemp(new WordVariable(NULL));
		}
	}

	public static class WordVariableAt extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordVariable array = (WordVariable) context.popTemp();
			IntegerConstant ndx = (IntegerConstant) context.popTemp();
			context.pushTemp(array.value);
		}
	}

	public static class WordVariablePut extends Word {
		@Override
		public void execute(ExecutionContext context) {
			WordVariable array = (WordVariable) context.popTemp();
			IntegerConstant ndx = (IntegerConstant) context.popTemp();
			Word newValue = context.popTemp();
			array.value = newValue;
		}
	}
}
