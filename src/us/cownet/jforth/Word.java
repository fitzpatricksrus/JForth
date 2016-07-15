package us.cownet.jforth;

public class Word {
	public String getName() {
		return getClass().getName();
	}

	public void execute(ExecutionContext context) {
		context.push(this);
	}

	public final Vocabulary getVocabulary() {
		return constructVocabulary();
	}

	protected SimpleVocabulary constructVocabulary() {
		return new SimpleVocabulary().addWord(new IdentityEquals<>());
	}

	//--------------------------
	// Vocabulary

	public static class IdentityEquals<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			context.push(new BooleanConstant(context.pop() == context.pop()));
		}
	}
}

