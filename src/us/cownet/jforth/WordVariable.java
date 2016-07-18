package us.cownet.jforth;

public class WordVariable extends Word {
	private Word value;

	public WordVariable(Word value) {
		this.value = value;
	}

	//--------------------------
	// Vocabulary

	public static class WordVariableCreate extends Word {
		// ( -- variable )
		@Override
		public void execute(ExecutionContext context) {
			context.push(new WordVariable(NULL));
		}
	}

	@Override
	protected Vocabulary constructVocabulary() {
		return constructVocabulary()
				.addWord("new", new WordVariable.WordVariableCreate())
				.addWord("at:", new WordVariable.WordVariableAt())
				.addWord("at:put:", new WordVariable.WordVariablePut());
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
