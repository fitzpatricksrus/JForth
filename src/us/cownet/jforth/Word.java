package us.cownet.jforth;

public class Word {
	public String getName() {
		return getClass().getName();
	}

	public void execute(ExecutionContext context) {
	}

	public final Vocabulary getVocabulary() {
		return constructVocabulary();
	}

	protected SimpleVocabulary constructVocabulary() {
		return new SimpleVocabulary();
	}

	public static class ExecuteTOS extends Word {
		public void execute(ExecutionContext context) {
			context.popTemp().execute(context);
		}
	}
}

