package us.cownet.jforth;

public class WordVariable extends Word {
	private Word value;

	public static final WordVariable NULL = new WordVariable(null);

	public WordVariable(Word value) {
		this.value = value;
	}

	//--------------------------
	// Vocabulary

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return constructVocabulary()
				.addWord(new WordVariable.WordVariableCreate())
				.addWord(new WordVariable.WordVariableAt())
				.addWord(new WordVariable.WordVariablePut());
	}

	public static class WordVariableCreate extends Word {
		// ( -- variable )
		@Override
		public void execute(ExecutionContext context) {
			context.push(new WordVariable(NULL));
		}
	}

	public static class WordVariableAt extends Word {
		// ( variable -- word )
		@Override
		public void execute(ExecutionContext context) {
			WordVariable variable = (WordVariable) context.pop();
			context.push(variable.value);
		}
	}

	public static class WordVariablePut extends Word {
		// ( word variable -- )
		@Override
		public void execute(ExecutionContext context) {
			WordVariable variable = (WordVariable) context.pop();
			variable.value = context.pop();
		}
	}
}
