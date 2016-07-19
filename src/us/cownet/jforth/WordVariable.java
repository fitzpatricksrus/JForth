package us.cownet.jforth;

public class WordVariable extends Word {
	private Word value;

	public WordVariable(Word value) {
		this.value = value;
	}

	//--------------------------
	// Vocabulary

	public static void WordVariableCreate(ExecutionContext context) {
		// ( -- variable )
		context.push(new WordVariable(NULL));
	}

	public static void WordVariableAt(ExecutionContext context) {
		// ( variable -- word )
		WordVariable variable = (WordVariable) context.pop();
		context.push(variable.value);
	}

	public static void WordVariablePut(ExecutionContext context) {
		// ( word variable -- )
		WordVariable variable = (WordVariable) context.pop();
		variable.value = context.pop();
	}
}
